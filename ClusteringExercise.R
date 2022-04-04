#install package dplyr first
#NOTE: do this only once, during your first run
install.packages("dplyr")

#load dplyr
library(dplyr)

#Example: calculating Euclidean distance
#create a random array of 6 numbers in the range 1~100
xarray <- c(sample(c(1:100),6))

#create distance distribution
xdist <- dist(xarray, method="euclidean")
#coerve into matrix format
xdistmat <- as.matrix(xdist)
#coerce into data fram
xdist.df <- as.data.frame(xdistmat)
#set row and column names 
names(xdist.df) <- xarray
row.names(xdist.df) <- xarray

#View distance matrix
xdist.df

################################################################################
# EXERCISE 1: create a similar distance distribution matrix for the first 10 Fibonacci numbers
# note: name the columns as 1 through 10 and don't rename the rows (why?)
################################################################################

################################################################################
# EXERCISE 2: complete the following tasks
################################################################################

#loading data
prop.df <- read.csv("properties.csv")
names(prop.df) <- c("x", "Address", "AreaName", "Price", "Lat", "Lng")

prop1.df <- prop.df[c(1:20),]
View(prop1.df)

#delete rows with Lat & Lng values of -999 (coordinates unavailable)
prop.df <- prop.df %>% filter(Lat != -999)
#viewing data
View(prop.df)

######################################################
# You Do: 
# 1) Normalize the price, lat and lng variables:
# create three new columns in prop.df named Price.norm, Lat.norm & Lng.norm
# make sure to normalize all of them to the range 0~1
######################################################
#
#
#
#
#
#
#

#create datasets for clustering analysis. consider geographical location
str(prop.df)
data.df <- prop.df[,-c(1:4,7:9)]
data.norm.df <- prop.df[,-c(1:7)]

View(data.df)
View(data.norm.df)

#create a small sample
set.seed(1)
sample.index <- sample(row.names(data.df),50)
data.sample.df <- data.df[sample.index,]
data.sample.norm.df <- data.norm.df[sample.index,]

View(data.sample.df)

#scatter plot
plot(data.sample.df)

#measure Euclidean distances
d <- dist(data.sample.df, method="euclidean")
d.norm <- dist(data.sample.norm.df, method="euclidean")

d.mat <- as.matrix(d)
d.norm.mat <- as.matrix(d.norm)
d.df <- as.data.frame(d.mat)
d.norm.df <- as.data.frame(d.norm.mat)

View(d.df)
# hierarchical clustering and dundelion plot
# in hclust() set argument method =  
# to "ward.D", "single", "complete", "average", "median", or "centroid"
hc1 <- hclust(d, method = "single")
plot(hc1, hang = -1, ann = FALSE)
hc2 <- hclust(d.norm, method = "average")
plot(hc2, hang = -1, ann = FALSE)


######################################################
# You do: 
# 2) create a distance matrix and a cluster plot for York Mills area
# 3) do the same, but for the 30 most expensive properties in the dataset
######################################################
######################################################

#####################################################
# You do: 
# 4) run kmeans algorithm. create a cluster plot 
# the codes for this part is already given. your job is to try it out
# make modifications to the code if and as needed

data.km.df <- prop.df[,-c(1:6)]
set.seed(2)
km <- kmeans(data.km.df, 6)

# show cluster membership
km$cluster

## centroids
km$centers

## plot an empty scatter plot
plot(c(0), xaxt = 'n', ylab = "", type = "l", 
     ylim = c(min(km$centers), max(km$centers)), xlim=c(0.7,3))

# label x-axes
axis(1, at=c(1,2,3), labels = names(data.km.df))

# plot centroids
for (i in c(1:6))
  lines(km$centers[i,], lty = i, lwd = 2, col = ifelse(i %in% c(1, 3, 5),
                                                       "black", "dark grey"))

# name clusters
text(x = 0.8, y = km$centers[, 1], labels = paste("Cluster", c(1:6)))

## distance from centers
dist(km$centers)