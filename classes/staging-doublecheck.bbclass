inherit sstate

python do_populate_sysroot_doublecheck() {
    import os

    if not d.getVar("PN").endswith("-native"):
        return
    manifest = d.expand("${SSTATE_MANIFESTS}/manifest-${BUILD_ARCH}-${PN}.populate_sysroot")
    if os.path.exists(manifest):
        content = []
        with open(manifest, "r") as f:
            content = f.readlines()
        with open(manifest, "w") as o:
            for line in content:
                _sline = line.strip("\n").strip()
                if os.path.exists(_sline):
                    o.write(line)
                else:
                    bb.warn("{} is missing but found in manifest".format(_sline))
}
do_populate_sysroot_doublecheck[doc] = "Double check manifest files for native packages"
addtask do_populate_sysroot_doublecheck after do_populate_sysroot before do_build