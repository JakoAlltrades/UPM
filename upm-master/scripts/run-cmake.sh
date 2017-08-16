#!/usr/bin/env bash

# Run cmake
cmake \
  -DSWIG_EXECUTABLE=/usr/bin/swig \
  -DBUILDDOC=$BUILDDOC \
  -DBUILDCPP=$BUILDCPP \
  -DBUILDFTI=$BUILDFTI \
  -DBUILDSWIGPYTHON=$BUILDSWIGPYTHON \
  -DBUILDSWIGNODE=$BUILDSWIGNODE \
  -DBUILDSWIGJAVA=$BUILDSWIGJAVA \
  -DBUILDEXAMPLES=$BUILDEXAMPLES \
  -DIPK=$IPK \
  -DRPM=$RPM \
  -DNPM=$NPM \
  -DBUILDTESTS=$BUILDTESTS \
  -DWERROR=$WERROR \
  -DCMAKE_EXPORT_COMPILE_COMMANDS=ON \
  -H. \
  -Bbuild
