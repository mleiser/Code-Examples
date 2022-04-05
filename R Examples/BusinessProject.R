ggplot(SystemAdministrators.df, aes(x=Training, y=Experience, color=`Completed task`)) + 
  geom_point()

SystemAdministrators.df$`Completed task` <- as.factor(SystemAdministrators.df$`Completed task`)

logit.reg <- glm(`Completed task` ~., data = SystemAdministrators.df, family = "binomial")
options(scipen = 999)
summary(logit.reg)

logit.reg.pred <- predict(logit.reg, SystemAdministrators.df, type = 'response')
data.frame(actual = SystemAdministrators.df$`Completed task`, predicted = logit.reg.pred)
