%module pyupm_si7005
%include "../upm.i"
// Include doxygen-generated documentation
%include "pyupm_doxy2swig.i"


%include "stdint.i"

%include "si7005.hpp"
%{
    #include "si7005.hpp"
%}
