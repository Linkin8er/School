import math
__slots__ = ['_W']

def __init__(self, size, edges=[], weights=[]) :
    """Initializes a weighted adjacency matrix for a graph with size nodes.

    Graph is initialized with size nodes and a specified set of
    edges and edge weights.
        
    Keyword arguments:
    size -- Number of nodes of the graph.
    edges -- a list of ordered pairs (2-tuples) indicating the
             edges of the graph.  The default value is an empty list
             which means no edges by default.
    weights -- a list of weights for the edges, which should be the same
               length as the edges list.  The position of a value in
               the weights list corresponds to the edge in the same
               position of the edges list.
    """
    #This block makes the matrix accoring to the size given. Easy 'nuff
    #correction, less easy because we are using __slots__ = ['_W']
    graph = []
    for i in range(size): 
        row=[] 
        for j in range(size): 
            row.append(math.inf)
            if( i == j) :  row[j] = 0    
                
    print(len(graph))
       
if __name__ == "__main__" :
    __init__(0, 9)
    
