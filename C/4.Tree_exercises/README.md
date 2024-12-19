# Tree Exercises

## Exercise 1:

* Input: A binary tree of integers where each node has a label (integer value).
* Output:

  - True: if for every node with two non-empty children, the node's label is equal to the sum of the labels of its two children.
  - False: otherwise.

## Exercise 2:

* Input:
  
  - A binary tree of characters where each node has a label (character).
  - A list of characters.
    
* Output:

  - True: if there exists a path from the root to a leaf in the tree such that the path's sequence of node labels matches the given list.
  - False: otherwise.
    
## Exercise 3:

* Input:
  
  - A tree Tree<E>, where each node contains an element of type E.
  - A predicate function that takes an element of type E and returns a boolean value.

* Output: A list List<Boolean> where the i-th element is
  
  - True: if all elements at level i of the tree satisfy the predicate.
  - False: otherwise.

## Exercise 4:

* Input: A tree Tree<E> n-ary, where each node contains an element of type E.
* Output: A Map<Integer, List<E>> where each entry corresponds to a level in the tree.
  - The key of each entry is the level number (an integer).
  - The value is a list of elements E representing the labels of nodes at that level that have an even number of children.
