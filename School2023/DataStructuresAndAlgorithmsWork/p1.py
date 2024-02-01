# Student name: Nicholas Stephani.
#
# Assignment Instructions: (DO NOT DELETE... you will lose points).
#
# Implement the insertion sort and heap sort algorithms in the
# functions that follow. Also implement the is_sorted function
# to check if a list is sorted, and the random_list function to
# generate a list of random integers. When you are working on
# implementing heap sort, in addition to the heap_sort function,
# you will likely want to begin by implementing all of its helper
# functions, which include: _build_max_heap, _max_heapify, _left,
# and _right. Carefully read the comments I have throughout, as I
# made a few minor adjustments relative to the textbook pseudocode.
# After implementing all of the functions, write some code in the
# if block at the very bottom of this file to test that your two
# sorts, your is_sorted, and your random_list works.
#
# Make sure that you carefully read the docstrings that I've provided
# for the functions, which specifies what you are to implement.
# DO NOT delete the docstrings (the multiline strings immediately after
# the signature lines of the functions). You will lose points if you
# delete the docstrings. You may delete comments that I inserted to give
# you hints.
#
# Don't change the names of any of the functions or the names of the
# parameters. 
#
# IMPORTANT: Remember that the textbook pseudocode uses 1-based indexing,
# while Python uses 0-based indexing, so you may need to make minor
# adjustments from the pseudocode (pay specific attention to the _left
# and _right helper functions for heap_sort, but those are not the only
# places where such adjustments are needed).  
#
# Note: You don't need the pass statements that I inserted, so you
# can delete them after you implement the functions.  I put them
# in temporarily so that you have valid syntax to start with.  A function
# requires at least one statement in the body.  The pass statement does
# nothing, but is a statement none-the-less.
#
# IMPORTANT: DO NOT have any print statements in the functions in this
# Python file (e.g., in the is_sorted, random_list, insertion_sort,
# heap_sort, and heap_sort's various helper functions).
# In general, you want to separate output from the computation.  You'll
# be outputting results (e.g., using print) in the if block at the very
# bottom of this module.

import random

def is_sorted(A) :
    """Returns True if A is sorted in non-decreasing order,
    and returns False if A is not sorted.
    
    Keyword arguments:
    A - a Python list.
    """
    
    sorted = True
    #This for loop runs through the array, checking to make sure each number in it is less than the next
    #
    for i in range(0, len(A)-1, +1):
        if (A[i] > A[i+1]) : sorted = False

    return sorted

def random_list(length, low=0, high=100) :
    """Generates and returns a Python list of random integer values.
    The integers in the list are generated uniformly at random from
    the interval [low, high], inclusive of both end points.
    
    Keyword arguments:
    length - the length of the list.
    low - the lower bound for the random integers.
    high - the upper bound for the random integers.
    """

    #This simply goes through a lsit and makes a random array of some set length
    randomArray = []
    for i in range(0, length, +1) : randomArray.append(random.randrange(low, high+1))
    return randomArray


def insertion_sort(A) :
    """Implementation of the insertion sort algorithm
    as specified on page 19 of the textbook.

    But keep in mind that the textbook pseudocode uses 1
    as the first index into an array, but Python list
    indexes begin at 0, so you will need to think how to
    adjust for that.
    
    Keyword arguments:
    A - a Python list.
    """
    
    for i in range(0, len(A), +1) :
        key = A[i]
		#insert A[i] into the sorted subarray A[0:i-1]
        j = i-1
        while j>=0 and A[j] > key :
            A[j+1]=A[j]
            j = j-1
        A[j+1] = key
    return A



def heap_sort(A) :
    """Implementation of the heapsort algorithm
    as specified on page 170 of the textbook.

    But keep in mind that the textbook pseudocode uses 1
    as the first index into an array, but Python list
    indexes begin at 0, so you will need to think how to
    adjust for that both in this function and its helpers.

    Keyword arguments:
    A - a Python list.
    """
    
    heap_size = _build_max_heap(A)
    for i in range((len(A)-1), -1, -1) :
        temp = A[0]
        A[0] = A[i]
        A[i] = temp
        heap_size = heap_size - 1
        _max_heapify(A, 0, heap_size)

    # I wrote the statement that corresponds to line 1
    # of the pseudocode from page 170 for you, mainly
    # because I'm altering how the heap_size is maintained
    # compared with how it is maintained in the textbook.
    # Multiple helper functions need it. The textbook assumes
    # that it is a property of A, but we cannot add fields to
    # a Python list. So _build_max_heap will initialize it
    # and return it, and then we'll pass it around as a parameter
    # wherever it is needed.



def _build_max_heap(A) :
    """Helper function for heap_sort.
    This should be mostly as described on page 167.
    The one modification is that this function should
    return the heap_size. The textbook version relies
    on A having it as a property, but we cannot do that
    here because A is a Python list and we cannot add
    properties to it. But other helper functions need
    access to it, so we will return it here, and then
    pass around as a parameter as needed.

    But keep in mind that the textbook pseudocode uses 1
    as the first index into an array, but Python list
    indexes begin at 0, so you will need to think how to
    adjust for that.

    Keyword arguments:
    A - The list we are sorting.
    """
    heap_size = len(A)
    for i in range((heap_size//2-1), -1, -1) :
        _max_heapify(A,i, heap_size)
    return heap_size


def _left(i) :
    """Returns the index of the left child of index i.

    But keep in mind that the textbook pseudocode uses 1
    as the first index into an array, but Python list
    indexes begin at 0, so you will need to think how to
    adjust for that.

    Keyword arguments:
    i - An index into the heap.
    """
    return (2*i) + 1

def _right(i) :
    """Returns the index of the right child of index i.

    But keep in mind that the textbook pseudocode uses 1
    as the first index into an array, but Python list
    indexes begin at 0, so you will need to think how to
    adjust for that.

    Keyword arguments:
    i - An index into the heap.
    """
    return (2*i)+2


def _max_heapify(A, i, heap_size) :
    """This is the helper function described with pseudocode
    on page 165 of the textbook. It is a little different than
    the textbook version. Specificially, it has a parameter for
    the heap_size.

    But keep in mind that the textbook pseudocode uses 1
    as the first index into an array, but Python list
    indexes begin at 0, so you will need to think how to
    adjust for that.

    Keyword arguments:
    A - The list to sort.
    i - The index.
    heap_size - The heap_size as used on page 165, but passed as
            a parameter.
    """
    l = _left(i)
    r = _right(i)
    if l < heap_size and A[l] > A[i] : largest = l
    else : largest = i
    if r < heap_size and A[r] > A[largest] : largest = r
    if largest != i :
        temp = A[i]
        A[i] = A[largest]
        A[largest] = temp
        _max_heapify(A, largest, heap_size)
    
    
if __name__ == "__main__" :
    ## Indented within this if block, do the following:
    ## 1) Write a few lines of code to demonstrate that your
    ##    is_sorted works correctly (i.e., that it returns True
    ##    if given a list that is sorted, and False otherwise).
    ## 2) Write a few lines of code to demonstrate that insertion_sort
    ##    correctly sorts a list (your random_list function will be useful
    ##    here).  Output (i.e., with print statements) the contents
    ##    of the list before sorting, and then again after sorting).
    ## 3) Repeat 2 to demonstrate that your heap_sort sorts correctly.
    bronus = random_list(10, 0, 100)

    print(bronus)

    isItSorted = is_sorted(bronus)

    if(isItSorted) : print("It was properly sorted!")
    if(not(isItSorted)) : print("hmmm, this doesn't seem to be sorted")

    insertion_sort(bronus)

    print(bronus)

    isItSorted = is_sorted(bronus)

    if(isItSorted) : print("It was properly sorted!")
    if(not(isItSorted)) : print("hmmm, this doesn't seem to be sorted")

    bronus = random_list(100, 0, 100)
    print(bronus)

    heap_sort(bronus)

    print(bronus)

    isItSorted = is_sorted(bronus)

    if(isItSorted) : print("It was properly sorted!")
    if(not(isItSorted)) : print("hmmm, this doesn't seem to be sorted")



