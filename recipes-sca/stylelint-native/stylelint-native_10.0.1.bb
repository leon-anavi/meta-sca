SUMMARY = "Native variant of stylelint"
DESCRIPTION = "Native build of stylelint."

SRC_URI = "file://stylelint.sca.description"

LICENSE = "MIT"
LIC_FILES_CHKSUM  = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

do_configure[noexec] = "1"

FILES_${PN} = "${datadir}/"

inherit native
inherit npm-helper

python do_compile() {
    # npm --prefix . install stylelint@0.11.0

    ## Install needed pkgs
    pkgs = {
        "wrappy": "1.0.2",
        "esutils": "2.0.2",
        "flat-cache": "2.0.1",
        "unist-util-find-all-after": "1.0.2",
        "hosted-git-info": "2.7.1",
        "unset-value": "1.0.0",
        "call-me-maybe": "1.0.1",
        "map-cache": "0.2.2",
        "concat-map": "0.0.1",
        "js-tokens": "4.0.0",
        "is-number": "3.0.0",
        "convert-source-map": "1.6.0",
        "remark": "10.0.1",
        "safe-buffer": "5.1.2",
        "known-css-properties": "0.13.0",
        "atob": "2.1.2",
        "component-emitter": "1.3.0",
        "pify": "4.0.1",
        "spdx-correct": "3.1.0",
        "array-find-index": "1.0.2",
        "is-plain-obj": "1.1.0",
        "split-string": "3.1.0",
        "ini": "1.3.5",
        "normalize-package-data": "2.5.0",
        "fast-deep-equal": "2.0.1",
        "browserslist": "4.5.5",
        "globals": "11.11.0",
        "@types/vfile-message": "1.0.1",
        "to-regex-range": "2.1.1",
        "resolve": "1.10.1",
        "get-stdin": "7.0.0",
        "jsesc": "2.5.2",
        "autoprefixer": "9.5.1",
        "xtend": "4.0.1",
        "decamelize": "1.2.0",
        "cache-base": "1.0.1",
        "postcss-syntax": "0.36.2",
        "locate-path": "2.0.0",
        "color-convert": "1.9.3",
        "domelementtype": "1.3.1",
        "is-accessor-descriptor": "0.1.6",
        "mkdirp": "0.5.1",
        "vfile-location": "2.0.4",
        "@nodelib/fs.stat": "1.1.3",
        "union-value": "1.0.0",
        "@babel/core": "7.4.4",
        "read-pkg": "3.0.0",
        "markdown-table": "1.1.2",
        "urix": "0.1.0",
        "to-object-path": "0.3.0",
        "num2fraction": "1.2.2",
        "is-alphanumeric": "1.0.0",
        "resolve-from": "3.0.0",
        "@types/glob": "7.1.1",
        "global-modules": "2.0.0",
        "lodash": "4.17.11",
        "is-data-descriptor": "0.1.4",
        "@babel/highlight": "7.0.0",
        "p-try": "1.0.0",
        "markdown-escapes": "1.0.2",
        "is-plain-object": "2.0.4",
        "caller-path": "2.0.0",
        "globby": "9.2.0",
        "once": "1.4.0",
        "source-map": "0.6.1",
        "remark-parse": "6.0.3",
        "escape-string-regexp": "1.0.5",
        "imurmurhash": "0.1.4",
        "callsites": "2.0.0",
        "unist-util-stringify-position": "1.1.2",
        "character-entities": "1.2.2",
        "is-hexadecimal": "1.0.2",
        "is-extendable": "0.1.1",
        "table": "5.2.3",
        "trim-trailing-lines": "1.1.1",
        "json-parse-better-errors": "1.0.2",
        "is-alphanumerical": "1.0.2",
        "is-arrayish": "0.2.1",
        "indent-string": "3.2.0",
        "domutils": "1.7.0",
        "redent": "2.0.0",
        "character-entities-legacy": "1.1.2",
        "uniq": "1.0.1",
        "glob": "7.1.3",
        "log-symbols": "2.2.0",
        "meow": "5.0.0",
        "esprima": "4.0.1",
        "isexe": "2.0.0",
        "postcss-scss": "2.0.0",
        "stylelint-config-standard": "18.3.0",
        "brace-expansion": "1.1.11",
        "ansi-regex": "4.1.0",
        "picomatch": "2.0.5",
        "stylelint": "10.0.1",
        "leven": "3.1.0",
        "error-ex": "1.3.2",
        "unherit": "1.1.1",
        "array-uniq": "1.0.3",
        "snapdragon-node": "2.1.1",
        "debug": "2.6.9",
        "path-type": "3.0.0",
        "path-dirname": "1.0.2",
        "collection-visit": "1.0.0",
        "@types/events": "3.0.0",
        "postcss-sass": "0.3.5",
        "currently-unhandled": "0.4.1",
        "@babel/code-frame": "7.0.0",
        "source-map-resolve": "0.5.2",
        "js-yaml": "3.13.1",
        "gonzales-pe": "4.2.4",
        "ignore": "4.0.6",
        "svg-tags": "1.0.0",
        "object-copy": "0.1.0",
        "inflight": "1.0.6",
        "use": "3.1.1",
        "quick-lru": "1.1.0",
        "bail": "1.0.3",
        "stylelint-config-recommended": "2.2.0",
        "merge2": "1.2.3",
        "normalize-selector": "0.2.0",
        "unified": "7.1.0",
        "path-is-absolute": "1.0.1",
        "caller-callsite": "2.0.0",
        "caniuse-lite": "1.0.30000963",
        "global-prefix": "3.0.0",
        "has-flag": "3.0.0",
        "ajv": "6.10.0",
        "@babel/helper-function-name": "7.1.0",
        "x-is-string": "0.1.0",
        "specificity": "0.4.1",
        "util-deprecate": "1.0.2",
        "json5": "2.1.0",
        "is-obj": "1.0.1",
        "clone-regexp": "1.0.1",
        "write": "1.0.3",
        "snapdragon-util": "3.0.1",
        "replace-ext": "1.0.0",
        "uri-js": "4.2.2",
        "has-values": "1.0.0",
        "camelcase": "4.1.0",
        "unist-util-visit": "1.4.0",
        "glob-parent": "3.1.0",
        "snapdragon": "0.8.2",
        "dom-serializer": "0.1.1",
        "@babel/parser": "7.4.4",
        "mathml-tag-names": "2.1.0",
        "postcss-jsx": "0.36.0",
        "trough": "1.0.3",
        "is-buffer": "1.1.6",
        "object.pick": "1.3.0",
        "postcss-resolve-nested-selector": "0.1.1",
        "semver": "5.7.0",
        "extglob": "2.0.4",
        "@babel/template": "7.4.4",
        "micromatch": "4.0.2",
        "slice-ansi": "2.1.0",
        "@mrmlnc/readdir-enhanced": "2.2.1",
        "argparse": "1.0.10",
        "read-pkg-up": "3.0.0",
        "is-supported-regexp-flag": "1.0.1",
        "regex-not": "1.0.2",
        "to-fast-properties": "2.0.0",
        "parse-json": "4.0.0",
        "supports-color": "5.5.0",
        "indexes-of": "1.0.1",
        "is-descriptor": "0.1.6",
        "fs.realpath": "1.0.0",
        "ms": "2.0.0",
        "emoji-regex": "8.0.0",
        "entities": "1.1.2",
        "trim-newlines": "2.0.0",
        "slash": "2.0.0",
        "flatted": "2.0.0",
        "postcss": "7.0.14",
        "ccount": "1.0.3",
        "yargs-parser": "10.1.0",
        "find-up": "2.1.0",
        "execall": "1.0.0",
        "braces": "3.0.2",
        "is-fullwidth-code-point": "3.0.0",
        "signal-exit": "3.0.2",
        "string_decoder": "1.2.0",
        "spdx-expression-parse": "3.0.0",
        "repeat-element": "1.1.3",
        "map-obj": "2.0.0",
        "safe-regex": "1.1.0",
        "extend-shallow": "2.0.1",
        "unist-util-visit-parents": "2.0.1",
        "inherits": "2.0.3",
        "decamelize-keys": "1.1.0",
        "ansi-styles": "3.2.1",
        "has-value": "1.0.0",
        "postcss-reporter": "6.0.1",
        "spdx-exceptions": "2.2.0",
        "map-visit": "1.0.0",
        "array-union": "1.0.2",
        "dir-glob": "2.2.2",
        "validate-npm-package-license": "3.0.4",
        "rimraf": "2.6.3",
        "is-extglob": "2.1.1",
        "json-schema-traverse": "0.4.1",
        "fast-json-stable-stringify": "2.0.0",
        "globjoin": "0.1.4",
        "state-toggle": "1.0.1",
        "sugarss": "2.0.0",
        "character-entities-html4": "1.1.2",
        "collapse-white-space": "1.0.4",
        "balanced-match": "1.0.0",
        "is-word-character": "1.0.2",
        "parse-entities": "1.2.1",
        "color-name": "1.1.3",
        "spdx-license-ids": "3.0.4",
        "@types/node": "11.13.8",
        "postcss-safe-parser": "4.0.1",
        "minimist-options": "3.0.2",
        "@types/unist": "2.0.3",
        "import-fresh": "2.0.0",
        "fast-glob": "2.2.6",
        "postcss-media-query-parser": "0.2.3",
        "trim": "0.0.1",
        "class-utils": "0.3.6",
        "postcss-selector-parser": "3.1.1",
        "which": "1.3.1",
        "is-directory": "0.3.1",
        "fill-range": "7.0.1",
        "@babel/helpers": "7.4.4",
        "remark-stringify": "6.0.4",
        "character-reference-invalid": "1.1.2",
        "is-decimal": "1.0.2",
        "object-visit": "1.0.1",
        "graceful-fs": "4.1.15",
        "@babel/helper-get-function-arity": "7.0.0",
        "readable-stream": "3.3.0",
        "ret": "0.1.15",
        "astral-regex": "1.0.0",
        "camelcase-keys": "4.2.0",
        "@babel/generator": "7.4.4",
        "copy-descriptor": "0.1.1",
        "node-releases": "1.1.17",
        "import-lazy": "3.1.0",
        "is-alphabetical": "1.0.2",
        "define-property": "0.2.5",
        "base": "0.11.2",
        "stringify-entities": "1.3.2",
        "extend": "3.0.2",
        "@babel/types": "7.4.4",
        "loud-rejection": "1.6.0",
        "dot-prop": "4.2.0",
        "arrify": "1.0.1",
        "longest-streak": "2.0.2",
        "path-exists": "3.0.0",
        "is-windows": "1.0.2",
        "p-limit": "1.3.0",
        "@types/vfile": "3.0.2",
        "unist-util-is": "2.1.2",
        "file-entry-cache": "5.0.1",
        "htmlparser2": "3.10.1",
        "@babel/traverse": "7.4.4",
        "posix-character-classes": "0.1.1",
        "glob-to-regexp": "0.3.0",
        "string-width": "3.1.0",
        "vfile-message": "1.1.1",
        "arr-union": "3.1.0",
        "assign-symbols": "1.0.0",
        "isarray": "1.0.0",
        "postcss-less": "3.1.4",
        "mixin-deep": "1.3.1",
        "fragment-cache": "0.2.1",
        "style-search": "0.1.0",
        "@babel/helper-split-export-declaration": "7.4.4",
        "postcss-value-parser": "3.3.1",
        "@types/minimatch": "3.0.3",
        "expand-brackets": "2.1.4",
        "normalize-range": "0.1.2",
        "strip-bom": "3.0.0",
        "is-regexp": "1.0.0",
        "sprintf-js": "1.0.3",
        "minimist": "1.1.3",
        "for-in": "1.0.2",
        "strip-indent": "2.0.0",
        "nanomatch": "1.2.13",
        "trim-right": "1.0.1",
        "get-value": "2.0.6",
        "source-map-url": "0.4.0",
        "strip-ansi": "5.2.0",
        "decode-uri-component": "0.2.0",
        "path-parse": "1.0.6",
        "postcss-markdown": "0.36.0",
        "resolve-url": "0.2.1",
        "pascalcase": "0.1.1",
        "to-regex": "3.0.2",
        "unist-util-remove-position": "1.1.2",
        "is-whitespace-character": "1.0.2",
        "postcss-html": "0.36.0",
        "minimatch": "3.0.4",
        "arr-diff": "4.0.0",
        "mdast-util-compact": "1.0.2",
        "vfile": "3.0.1",
        "html-tags": "2.0.0",
        "is-glob": "4.0.1",
        "array-unique": "0.3.2",
        "arr-flatten": "1.1.0",
        "chalk": "2.4.2",
        "static-extend": "0.1.2",
        "isobject": "3.0.1",
        "p-locate": "2.0.0",
        "set-value": "0.4.3",
        "domhandler": "2.4.2",
        "punycode": "2.1.1",
        "load-json-file": "4.0.0",
        "repeat-string": "1.6.1",
        "cosmiconfig": "5.2.0",
        "kind-of": "3.2.2",
        "electron-to-chromium": "1.3.127"
    }


    for name, version in pkgs.items():
        npm_install_package(d, d.getVar("B"), name, version)

    ## Strip of hardcoded paths in packages
    npm_postinst_fix_paths(d, d.getVar("B"), "stylelint")
}

INSANE_SKIP_${PN} += "host-user-contaminated"

do_install() {
    mkdir -p ${D}${datadir}/stylelint
    cp -Ra ${B}/* ${D}${datadir}/stylelint
    install ${WORKDIR}/stylelint.sca.description ${D}${datadir}
}

