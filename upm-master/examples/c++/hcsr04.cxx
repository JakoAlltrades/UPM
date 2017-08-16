/*
 * Author: Yevgeniy Kiveisha <yevgeniy.kiveisha@intel.com>
 * Copyright (c) 2014 Intel Corporation.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

#include <unistd.h>
#include <iostream>
#include "hcsr04.hpp"
#include <signal.h>
#include <stdlib.h>
#include <sys/time.h>

int shouldRun = true;

void
sig_handler(int signo)
{
    if (signo == SIGINT) {
        shouldRun = false;
    }
}

//! [Interesting]
int
main(int argc, char **argv)
{
    upm::HCSR04 *sonar = new upm::HCSR04(2, 4);
    signal(SIGINT, sig_handler);

    sleep(1);

    while(shouldRun){
        std::cout << "get distance" << std::endl;
        double distance = sonar->getDistance(HCSR04_CM);
        std::cout << "distance " << distance << std::endl;
        sleep(2);
    }
    std::cout << "Exiting... " << std::endl;

    delete sonar;
    return 0;
}
//! [Interesting]
