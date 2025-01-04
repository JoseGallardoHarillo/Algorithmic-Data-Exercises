# Graph Exercises

## Exercise 1:

* Input: A small imaginary social network represented by a graph where vertices model the members of the network, and edges (without labels or weights) model the friendships between two members.
* Output:

  - a: Two sets, one with the members who do not have any friends, another with the members who have the highest number of friends. (Display the graph by configuring its appearance to highlight in one color the members who have no friends and in another color the members who have the highest number of friends.)

  - b: Given two members, returns the shortest list of friends between them. (Display the graph by highlighting the shortest list of friends between the two members.)

  - c: how many groups of members there are and what their composition is. Two members belong to the same group if they are directly connected by friendship or if there are intermediate members who connect them. (Display the graph by coloring each group with a different color.)
 
  - d: A set of questionnaires has been designed for the members to fill out regarding their friendships. These questionnaires need to be sent to a selected set of members such that: all friendships are represented (a friendship relation is represented by one of its two members), and the number of questionnaires to be sent is minimized (i.e., the number of selected members is minimized). (Display the graph by coloring the selected members.)

## Exercise 2:

* Input: A set of groups taught by a set of professors. Each professor teaches several groups, and each group is taught by two professors.
    
* Output:

  - a: which groups should be taught in parallel and how many time slots are needed, taking into account that groups taught by the same professor cannot be scheduled in parallel.
  - b: Represent the problem as a graph where the vertices are the groups, and configure the graph's appearance so that the vertices are colored according to the time slot in which they are taught.
    
## Exercise 3:

* Input: A curriculum structured such that students cannot freely enroll in courses of their choice. Some courses have prerequisites, which must be completed and passed before enrolling in the next course. A course may have 0 or more prerequisite courses.

* Output:
  
  - a: A list of all courses in an order that satisfies the given prerequisites.
  - b: The set of courses that can be taken without any prerequisites. (Display the graph by highlighting these courses.)
  - c: Given a set of courses that a student has passed, returns a set of courses that the student can take next year. (Display the graph by highlighting the courses from both sets with two different colors.)
