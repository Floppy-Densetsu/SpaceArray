# SpaceArray
A system which passes data around between Boolean arrays within a 3D array. Intended to reflect individual points of space with multiple angles of energy transfer, represented by the concept of multi-faceted shapes with a facet numbering system.

Sorry that the whole thing is a mess. There is a lot of unused code that had convoluted purposes which don't necessarily apply now.
The display systems are difficult to explain as well. The one Test uses shows each location spread out with 0-24 in a row, then 25-49 in the next row down, then 50-74 in the row below that one, 75-99 beneath that, and 100-124 in the fifth row down from the first. Then it starts again with the next space along the Y axis. To the right of each of these, the next X space starts and does the same thing.

So you have 

            Y0,X0:0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24, then Y0,X1:0,1,2,3,4,5,6, etc.
            Y0,X0:25,26,27,28,29,30...
            Y0,X0:50,51,52,53,54,55,56,57,58...
            ...
            ...
            Y1,X0:0,1,2,3,4,5,6,7,8,9...
            
            and so on.
            
Each Z layer is represented by the different size squares, where if I remember right, Z0 is the tiniest square in the center while the max Z is the larger square surrounding the smaller ones. This was done so that I could see the different depths represented in the same X,Y, locations.

The other functional piece is ColorTest, where I changed it so that depth is exhibited using color, and each face location is drawn as one pixel. The color of the pixel is defined by how many Z positions at that Y,X location have that face turned on at that time. It also depends upon whether it is an Out or an In face that is turned on, or both. There should not be more Z layers added to this without rewriting the display code, because it is designed so that when everything is turned on, all the color values add up to 253. Increasing the MAXZ value any more will cause the display to try to draw a color value beyond 255.

I had hoped to make this into an atomic simulator or some kind of particle game one day, but I need a job and this just takes time away from the mundane things that actually have to get done so I don't turn out broke and homeless. It is unwieldy and clumsy, and I imagine that if a C++ writer cared to, they could structure the memory in a much more efficient way. 
