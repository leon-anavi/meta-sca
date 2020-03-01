#!/bin/usr/env python3

# BSD 2-Clause License
#
# Copyright (c) 2020, Konrad Weihmann
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are met:
#
# * Redistributions of source code must retain the above copyright notice, this
#   list of conditions and the following disclaimer.
#
# * Redistributions in binary form must reproduce the above copyright notice,
#   this list of conditions and the following disclaimer in the documentation
#   and/or other materials provided with the distribution.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
# AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
# IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
# DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
# FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
# DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
# SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
# CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
# OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
# OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

import argparse
import json
import os
import re
import stat

import magic


def warning(id, obj, msg):
    print("WARNING: [{}]: {}: {}".format(id, obj, msg))


def info(obj, msg):
    print("INFO: {}: {}".format(obj, msg))


def rel_path(root, obj, _args):
    return os.path.relpath(os.path.join(root, obj), os.path.dirname(_args.directory))


def create_parser():
    parser = argparse.ArgumentParser(description='Enhanced package-qa')
    parser.add_argument("--debug", default=False,
                        action="store_true", help="Debug output")
    parser.add_argument("config", help="Configuration file")
    parser.add_argument("directory", help="Directory to check")

    args = parser.parse_args()
    with open(args.config) as i:
        try:
            args.config = json.load(i)
        except json.JSONDecodeError:
            raise Exception("Configuration file isn't a valid json")
    return args


def walk_dir(_args):
    for root, dirs, files in os.walk(_args.directory):
        for d in dirs:
            if _args.debug:
                info(rel_path(root, d, _args), "Directory found")
            if _args.config["acceptableDirs"]:
                if not any([d.startswith(x.lstrip("/")) for x in _args.config["acceptableDirs"]]):
                    warning("unacceptable-dir", rel_path(root, d, _args),
                            "Directory is not in acceptable paths")
            if any([d.startswith(x.lstrip("/")) for x in _args.config["blacklistDirs"]]):
                warning("blacklist-dir", rel_path(root, d, _args),
                        "Directory is blacklisted")
        for f in files:
            _filename = os.path.join(root, f)
            _filemode = int(
                oct(stat.S_IMODE(os.stat(_filename).st_mode)), 8) & 0x1ff
            _, _ext = os.path.splitext(_filename)
            _basename = os.path.basename(_filename)
            _script = False
            try:
                with open(_filename) as i:
                    _cnt = i.readline(1024)
                    if _cnt:
                        if re.match(r"^#!/.*", _cnt):
                            _script = True
            except Exception:
                pass

            try:
                _mime = magic.from_file(_filename, mime=True)
            except magic.MagicException as e:
                print(e)
                _mime = "unknown/unknown"

            if _args.debug:
                info(rel_path(root, f, _args),
                     "mode: {}, mime: {}".format(oct(_filemode), _mime))
            _maxItem = None
            _minItem = None
            if _mime in _args.config["maxMask"].keys():
                _maxItem = _args.config["maxMask"][_mime]
            elif _ext in _args.config["maxMask"].keys():
                _maxItem = _args.config["maxMask"][_ext]
            elif _script and "script" in _args.config["maxMask"].keys():
                _maxItem = _args.config["maxMask"]["script"]
            elif "default" in _args.config["maxMask"].keys():
                _maxItem = _args.config["maxMask"]["default"]
            if _mime in _args.config["minMask"].keys():
                _minItem = _args.config["minMask"][_mime]
            elif _ext in _args.config["minMask"].keys():
                _minItem = _args.config["minMask"][_ext]
            elif _script and "script" in _args.config["minMask"].keys():
                _minItem = _args.config["minMask"]["script"]
            elif "default" in _args.config["minMask"].keys():
                _minItem = _args.config["minMask"]["default"]
            if _maxItem:
                _cmode = int(_maxItem, 8)
                if _filemode > _cmode:
                    warning("too-permissive", rel_path(root, f, _args),
                            "Too permissive filemode {}. Allowed maximum {}".format(oct(_filemode), oct(_cmode)))
            if _minItem:
                _cmode = int(_minItem, 8)
                if _filemode < _cmode:
                    warning("too-restrictive", rel_path(root, f, _args),
                            "Too resrictive filemode {}. Allowed minimum {}".format(oct(_filemode), oct(_cmode)))

            if any(_args.config["blacklistFiles"]) and \
               any([x in _args.config["blacklistFiles"] for x in [_mime, _ext, _basename]]):
                warning("blacklisted-file", rel_path(root, f, _args),
                        "Blacklisted file mime:'{}' found".format(_mime))


if __name__ == '__main__':
    _args = create_parser()
    walk_dir(_args)