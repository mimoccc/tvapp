#!/bin/sh
#
#  Copyright (c) Milan Jurkulák 2024.
#  Contact:
#  e: mimoccc@gmail.com
#  e: mj@mjdev.org
#  w: https://mjdev.org
#
set -eu
#
TEMP_REPO_DIR="$GITHUB_REPOSITORY$GITHUB_SHA"
#
if [ -z "$WIKI_DIR" ]; then
    echo "Wiki location is not specified, using default wiki/"
    WIKI_DIR='wiki'
fi
#
if [ -z "$GH_TOKEN" ]; then
    echo "Token is not specified"
    exit 1
fi
# Clone wiki repo
echo "Cloning wiki repo https://github.com/$GITHUB_REPOSITORY.wiki.git"
#
git clone "https://$GITHUB_ACTOR:$GH_TOKEN@github.com/$GITHUB_REPOSITORY.wiki.git" "$TEMP_REPO_DIR"
# Copy wikis
echo "Copying edited wiki"
cp -R "$WIKI_DIR/" "$TEMP_REPO_DIR"
# Get commit details
author=`git log -1 --format="%an"`
email=`git log -1 --format="%ae"`
message=`git log -1 --format="%s"`
# Changes check
echo "Checking if wiki has changes"
cd "$TEMP_WIKI_DIR"
git config --local user.email "$email"
git config --local user.name "$author"
git add .
if git diff-index --quiet HEAD; then
  echo "Nothing changed"
  exit 0
fi
# Push
echo "Pushing changes to wiki"
git commit -m "$message" && git push "https://$GITHUB_ACTOR:$GH_TOKEN@github.com/$GITHUB_REPOSITORY.wiki.git"
# Done