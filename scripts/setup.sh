#!env sh
devconf_sample_dir="./devconf_sample"
project_root=$(git rev-parse --show-toplevel)

cd ${project_root}

cp -r ${devconf_sample_dir}/.devcontainer ./.devcontainer
cp -r ${devconf_sample_dir}/.vscode ./.vscode
cp ${devconf_sample_dir}/.env ./.env
