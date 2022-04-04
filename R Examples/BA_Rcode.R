library(readxl)
train <- as.data.frame(read_excel("~/Documents/train.xlsx"))
View(train)

valid <- data.frame(stat = 0, IT = 1, other = 0, years = 1, course = NA)

library(class)
nn <- class::knn(train=train[, 1:4], test=as.data.frame(valid[, 1:4]), cl=train[, 5], k = 1)
length(train[, 1:4])
length(train[, 5])

row.names(train)[attr(nn, "nn.index")]

nn3 <- class::knn(train = as.data.frame(train.norm.df[, 1:7]), test = as.data.frame(new.norm.df[, 1:7]), cl = as.data.frame(train.norm.df[, 8]), k = 1)

accuracy.df <- data.frame(k = seq(1, 14, 1), accuracy = rep(0, 14))
knn.pred <- as.factor(knn.pred)
valid.norm.df[, 8] <- as.factor(valid.norm.df[, 8])
for (i in 1:5) {
  knn.pred <- class::knn(train = as.data.frame(train.norm.df[, 1:12]), 
                         test = as.data.frame(valid.norm.df[, 1:12]),
                         cl = as.data.frame(train.norm.df[, 13]), k = i) 
  confusionMatrix(knn.pred, valid.norm.df[, 13])$overall[1]
}
knn.pred.5 <- knn(train.norm.df[, c(1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12)], 
                valid.norm.df[, c(1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12)],
                train.norm.df[, 8], k = 5) 
test <- confusionMatrix(knn.pred, valid.norm.df[, 8])

for (i in 1:5) {
  knn.pred1 <- class::knn(train = train.norm.df[, 1:12], test = valid.norm.df[, 1:12], cl = train.norm.df[, 13], k = i)
  accuracy.df[i, 2] <- confusionMatrix(knn.pred1, valid.norm.df[, 13])$overall[1]  
}