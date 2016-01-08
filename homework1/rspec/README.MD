# Assignment 1 RSpec Tests

To run ensure that Ruby, RSpec, and jruby are installed then run these commands in the root of your homework 1 project

$ mkdir rspec
$ cd rspec
$ javac ../src/hw1/AlarmClock.java -d .
jruby -S rspec alarm_clock_spec.rb --color --format documentation 

Provided is an sh file that does the above. Just run the following to get in running

UNIX
$ chmod +x rspec/run_specs.sh