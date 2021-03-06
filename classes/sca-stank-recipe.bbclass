## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

inherit sca-helper
inherit sca-global
inherit sca-stank-core
inherit sca-tracefiles

SCA_DEPLOY_TASK = "do_sca_deploy_stank_recipe"

python do_sca_deploy_stank_recipe() {
    sca_conv_deploy(d, "stank")
}

do_sca_stank_core[doc] = "Lint shell scripts with stank"
do_sca_stank_core_report[doc] = "Report findings from do_sca_stank_core"
do_sca_deploy_stank_recipe[doc] = "Deploy results of do_sca_stank_core"
addtask do_sca_stank_core after do_compile before do_sca_tracefiles
addtask do_sca_stank_core_report after do_sca_tracefiles
addtask do_sca_deploy_stank_recipe after do_sca_stank_core_report before do_package

DEPENDS += "sca-recipe-stank-rules-native"
