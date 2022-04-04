ALTER TABLE [dbo].[Account] DROP CONSTRAINT [CK_Account]
GO

/****** Object:  Table [dbo].[Account]    Script Date: 10/10/2019 4:48:08 PM ******/
DROP TABLE [dbo].[Account]
GO

/****** Object:  Table [dbo].[Account]    Script Date: 10/10/2019 4:48:08 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Account](
	[AccountId] [int] NOT NULL,
	[CustomerId] [int] NULL,
	[OpenDate] [datetime] NULL,
	[AccountType] [varchar](50) NULL,
	[Balance] [decimal](18, 0) NULL,
	[Comment] [varchar](255) NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[AccountId] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [CK_Account] CHECK  (([AccountType]='Checking' OR [AccountType]='Savings' OR [AccountType]='Brokerage'))
GO

ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [CK_Account]
GO

