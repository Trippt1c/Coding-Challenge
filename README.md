# ACM Research Coding Challenge (Fall 2020)

## User Info
Dylan Trang

Freshman, CS

## Method used
K-means clustering algorithm

## Optimal Clustering
Personal Hypothesis: 2

Algorithm results: 4

## Approach
I'm familiar with the basic basics of machine learning, but this is my first attempt at implementing it, so apologies for the rough code.
I originally tried to do this without the help of any libraries, but the task proved too long to try and do it by the deadline so I caved and used **Java ML** and it's supporting libraries. K-means is simple but seems flawed to the human eyes for this given dataset, as it groups based on proximity to a centroid rather than by the density of a grouping like our eyes do. I still believe this is a reasonable approach for clustering, although a Gaussian mixture model may be more appropriate. I had trouble finding the optimal K using the elbow method but devised a rough solution through trial and error. Given more guidance and time I could find a better solution for implemeting the silhouette method to find a more precise K.
### Note
This program will create a new file called ClusterPlotFixed.csv.
This is because the original provided file was not read well by the library's file reader, so the choice was to either modify the file or modify the library. I didn't want to do either so I ended up creating a new file that is essential the same as the old file but with the first line (",V1,V2") deleted.

## Libraries used
Java ML and supporting libraries
