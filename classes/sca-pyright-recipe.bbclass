## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

inherit sca-pyright-core
inherit sca-global
inherit sca-tracefiles

inherit python3-dir

SCA_DEPLOY_TASK = "do_sca_deploy_pyright_recipe"

python do_sca_deploy_pyright_recipe() {
   sca_conv_deploy(d, "pyright")
}

do_sca_pyright_core[doc] = "Lint python code with pyright"
do_sca_pyright_core_report[doc] = "Report findings from do_sca_pyright_core"
do_sca_deploy_pyright_recipe[doc] = "Deploy results of do_sca_pyright_core"
addtask do_sca_pyright_core after do_compile before do_sca_tracefiles
addtask do_sca_pyright_core_report after do_sca_tracefiles
addtask do_sca_deploy_pyright_recipe after do_sca_pyright_core_report before do_package

DEPENDS += "sca-recipe-pyright-rules-native"
