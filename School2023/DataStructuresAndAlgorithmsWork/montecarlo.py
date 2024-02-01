import random
import math
import multiprocessing

# Programming Assignment 5
#
# Student Name: Nicholas Stephani
#
# Do not delete this comment containing the assignment instructions.
#
# What to submit:
# (a) This montecarlo.py file
#     As always, you are not allowed to change the names of
#     py files I've given you, functions, parameters, etc.
# (b) A text file with the output from when you run your
#     generate_table and time functions.  One text file with
#     both output tables is fine.
#
# 1) Implement the function pi_monte_carlo to estimate
#    the value of pi using Monte Carlo simulation.
#    See the details of how to do this in Blackboard,
#    which shows the sum that you need to compute.
#    You will need to import the random module.  Take a
#    look at the documentation of the random module to find
#    the function that generates random floating-point
#    values in the interval [0.0, 1.0).
#
#    IMPORTANT NOTE: Several different Monte Carlo algorithms
#    exist for estimating pi. One of which is described by the
#    equation I have in Blackboard with a very specific summation.
#    It happens to be one of the better ones that exist, but it
#    is also not the one you would likely find if you attempted
#    to Google for this. If you implement a different Monte Carlo
#    algorithm for estimating pi other than the one specified in
#    the assignment, then you will lose all points related to this
#    part of the assignment.
#
# 2) Implement a parallel version of this in the function
#    pi_parallel_monte_carlo.The second parameter, processes,
#    indicates how many processes to use. You should use
#    a Pool (see the parallel examples for the import that you
#    will need). The easiest ways to do this is to either use
#    the apply_async method of the Pool class or the map method
#    of the Pool class.
#
#    Hint 1: If you use apply_async, you'll start by determining
#           how many samples per process, which you can compute
#           from n and p.  You would then call apply_async p times
#           to have p processes call pi_monte_carlo (the sequential
#           version) using the number of samples necessary to spread
#           the n samples across p processes. Once you call apply_async
#           p times (make sure you store the Future objects that those
#           calls return in a list), you'll call get() on each of those
#           Future objects, and average the p results.
#
#    Hint 2: If you want to use Pool.map, then start the same
#           way by determining how many samples to use for each
#           process. Create your Pool with p processes.  Generate
#           a list of length p where the elements are the numbers of
#           samples for each process, which should sum to n.
#           Call pool.map (assuming your Pool is named pool) to map
#           your sequential pi_monte_carlo to that list.
#           When pool.map returns, compute the average of the p
#           results and return it.
#
#    Hint 3: Make sure you use a with block for your Pool (see examples
#           in video and corresponding sourcecode) to ensure the Pool
#           is closed.
#
# 3) Implement the generate_table function as specified below.
#
# 4) Implement the time function as specified below.
#
# 5) Run your generate_table and time functions from the shell
#    and save the output to a textfile.
#
# 6) Are the results what you expected to see? If so, why?
#    If not, why do you think your results are different
#    then you expected? You can just answer in a comment.
#
# 7) Submit the .py file and the textfile with the output.

def pi_monte_carlo(n) :
    """Computes and returns an estimation of pi
    using Monte Carlo simulation.

    Keyword arguments:
    n - The number of samples.
    """

    sum = 0

    for i in range (n):
        uniform = random.random()
        sum += math.sqrt(1 - (uniform*uniform))

    estimate = (4/n) * sum
    print(estimate)
    return estimate

def pi_parallel_monte_carlo(n, p=4) :
    """Computes and returns an estimation of pi
    using a parallel Monte Carlo simulation.

    Keyword arguments:
    n - The total number of samples.
    p - The number of processes to use.
    """
    
    # You can distribute the work across p
    # processes by having each process
    # call the sequential version, where
    # those calls divide the n samples across
    # the p calls.
    # Once those calls return, simply average
    # the p partial results and return that average.
    pass

def generate_table() :
    """This function should generate and print a table
    of results to demonstrate that both versions
    compute increasingly accurate estimations of pi
    as n is increased.  It should use the following
    values of n = {12, 24, 48, ..., 12582912}. That is,
    the first value of n is 12, and then each subsequent
    n is 2 times the previous.  The reason for starting at 12
    is so that n is always divisible by 1, 2, 3, and 4.
    The first
    column should be n, the second column should
    be the result of calling piMonteCarlo(n), and you
    should then have 4 more columns for the parallel
    version, but with 1, 2, 3, and 4 processes in the Pool."""
    pass

def time() :
    """This function should generate a table of runtimes
    using timeit.  Use the same columns and values of
    n as in the generate_table() function.  When you use timeit
    for this, pass number=1 (because the high n values will be slow)."""
    pass

pi_monte_carlo(50000000)