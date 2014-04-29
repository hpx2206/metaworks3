#!/bin/sh

echo "tempdir     : $1"
echo "projectdir  : $2"
echo "bundle name : $3"

cd $1
mvn  install -Dprojectdir=$2 -Dbundle.name=$3


