#!/bin/bash
#
# Copyright (c) Milan Jurkulák 2023.
# Contact:
# e: mimoccc@gmail.com
# e: mj@mjdev.org
# w: https://mjdev.org
#
LOG_VERSION=$1
LOG="wiki/Release-Notes-$LOG_VERSION.md"
LOG_ROOT='release-notes.txt'
TEMP=release-notes.temp
echo "# Release Notes $LOG_VERSION" > ${LOG}
git log --oneline $(git describe --abbrev=0 --tags 2>&1).. >> ${LOG}
cut -d' ' -f2-  ${LOG} >> ${TEMP}
while read -r line
do
    echo "* $line"
done <${TEMP} > ${LOG}
rm ${TEMP}
cp ${LOG} ${LOG_ROOT}