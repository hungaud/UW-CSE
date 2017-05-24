1) Describe the worst-case asymptotic running times of your methods adjacentVertices,
edgeCost, and shortestPath. In your answers, use |E| for the number of edges
and |V| for the number of vertices. Explain and justify your answers.

Answer: The worst case asymptotic running time of adjacentVertices is O(1) if the VertexMap has been properly initialized
and the adjacent list is already in the Map. Otherwise the worst case running time is O(|E|) as the vertex needs to be compared to all the edges.
The worst-case asymptotic running time for edgeCost is O(|E|) since to get the cost of an edge you have to compare the two vertex against
all the edges.
The worst case running time for the shortest path is O(|V|^2) since in our while loop the worst case is all the vertices are unknown and to find the Vertex
with the smallest cost you must go through the unknown vertices. Therefore you are checking against the total number of vertices twice so shortestPath is O(|V|^2)

2) Describe how you tested your code.

Answer: To test our shortestPath we made println statements to test if the while loop was exiting at the correct time. We also made use of the
toString methods of Vertex and Edge to help us debug what was happening. After we thought the shortestPath worked we tested it on a smaller graph which we 
knew all the shortest path for. 

3) If you worked with a partner, describe how you worked together. If you divided up
the tasks, explain how you did so. If you worked on parts together, describe the actual
process. Discuss how much time you worked together and how you spent that time
(planning, coding, testing, ...)

Answer: As a partnership we brainstormed and created ideas together using a whiteboard and marker. After one member thought of an idea we would discuss
and draw out if the method would work. For the entire project we would stick to our method. The important part was making sure the code would run before
trying to test and improve the code. We spent 20 hours over the weekend creating this project and about 50% of it was spent on planning, 25% was spent on 
coding and the final 25% was spent on testing/debugging.  

4)If you did any extra credit, describe what you did.

Answer: For the extra credit we took all the non-major cse classes available and turned it in to a graph. The vertices were the classes and the edges
were the two classes and the number of credits the pre-requirement class is. 

