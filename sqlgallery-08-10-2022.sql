USE [master]
GO
/****** Object:  Database [gallery_art]    Script Date: 10/08/2022 09:18:35 ******/
CREATE DATABASE [gallery_art] ON  PRIMARY 
( NAME = N'gallery_art', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\gallery_art.mdf' , SIZE = 2304KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'gallery_art_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\gallery_art_log.LDF' , SIZE = 768KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [gallery_art] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [gallery_art].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [gallery_art] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [gallery_art] SET ANSI_NULLS OFF
GO
ALTER DATABASE [gallery_art] SET ANSI_PADDING OFF
GO
ALTER DATABASE [gallery_art] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [gallery_art] SET ARITHABORT OFF
GO
ALTER DATABASE [gallery_art] SET AUTO_CLOSE ON
GO
ALTER DATABASE [gallery_art] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [gallery_art] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [gallery_art] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [gallery_art] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [gallery_art] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [gallery_art] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [gallery_art] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [gallery_art] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [gallery_art] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [gallery_art] SET  ENABLE_BROKER
GO
ALTER DATABASE [gallery_art] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [gallery_art] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [gallery_art] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [gallery_art] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [gallery_art] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [gallery_art] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [gallery_art] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [gallery_art] SET  READ_WRITE
GO
ALTER DATABASE [gallery_art] SET RECOVERY SIMPLE
GO
ALTER DATABASE [gallery_art] SET  MULTI_USER
GO
ALTER DATABASE [gallery_art] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [gallery_art] SET DB_CHAINING OFF
GO
USE [gallery_art]
GO
/****** Object:  Table [dbo].[customer]    Script Date: 10/08/2022 09:18:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[customer](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[FullName] [varchar](100) NULL,
	[Email] [varchar](100) NULL,
	[Password] [varchar](100) NULL,
	[Status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[customer] ON
INSERT [dbo].[customer] ([Id], [FullName], [Email], [Password], [Status]) VALUES (1, N'thinh', N'thinh@gmail.com', N'202CB962AC59075B964B07152D234B70', 0)
INSERT [dbo].[customer] ([Id], [FullName], [Email], [Password], [Status]) VALUES (2, N'Minh Le Tien', N'minh@gmail.com', N'202CB962AC59075B964B07152D234B70', 0)
INSERT [dbo].[customer] ([Id], [FullName], [Email], [Password], [Status]) VALUES (3, N'Nhi', N'nhi@gmail.com', N'202CB962AC59075B964B07152D234B70', 0)
INSERT [dbo].[customer] ([Id], [FullName], [Email], [Password], [Status]) VALUES (4, N'Linh', N'linh@gmail.com', N'202CB962AC59075B964B07152D234B70', 0)
INSERT [dbo].[customer] ([Id], [FullName], [Email], [Password], [Status]) VALUES (5, N'Tuan', N'tuan@gmail.com', N'202CB962AC59075B964B07152D234B70', 0)
INSERT [dbo].[customer] ([Id], [FullName], [Email], [Password], [Status]) VALUES (6, N'Hai', N'hai@gmail.com', N'202CB962AC59075B964B07152D234B70', 0)
INSERT [dbo].[customer] ([Id], [FullName], [Email], [Password], [Status]) VALUES (7, N'Lien', N'lien@gmail.com', N'202CB962AC59075B964B07152D234B70', 0)
INSERT [dbo].[customer] ([Id], [FullName], [Email], [Password], [Status]) VALUES (8, N'Tien', N'tien@gmail.com', N'202CB962AC59075B964B07152D234B70', 0)
INSERT [dbo].[customer] ([Id], [FullName], [Email], [Password], [Status]) VALUES (9, N'Cong', N'cong@gmail.com', N'202CB962AC59075B964B07152D234B70', 0)
INSERT [dbo].[customer] ([Id], [FullName], [Email], [Password], [Status]) VALUES (10, N'Thang', N'thang@gmail.com', N'202CB962AC59075B964B07152D234B70', 1)
INSERT [dbo].[customer] ([Id], [FullName], [Email], [Password], [Status]) VALUES (11, N'Dinh Van Thanh', N'thanh@gmail.com', N'202CB962AC59075B964B07152D234B70', 0)
INSERT [dbo].[customer] ([Id], [FullName], [Email], [Password], [Status]) VALUES (12, N'Ngoc Mai', N'mai@gmail.com', N'202CB962AC59075B964B07152D234B70', 0)
SET IDENTITY_INSERT [dbo].[customer] OFF
/****** Object:  Table [dbo].[category]    Script Date: 10/08/2022 09:18:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[category](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[category] ON
INSERT [dbo].[category] ([Id], [Name]) VALUES (1, N'Digital painting')
INSERT [dbo].[category] ([Id], [Name]) VALUES (2, N'WaterColor')
INSERT [dbo].[category] ([Id], [Name]) VALUES (3, N'Oil painting')
INSERT [dbo].[category] ([Id], [Name]) VALUES (4, N'Wooden')
INSERT [dbo].[category] ([Id], [Name]) VALUES (5, N' silk painting')
SET IDENTITY_INSERT [dbo].[category] OFF
/****** Object:  Table [dbo].[users]    Script Date: 10/08/2022 09:18:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[users](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[FullName] [varchar](100) NULL,
	[Email] [varchar](100) NULL,
	[Role] [int] NOT NULL,
	[Password] [nvarchar](max) NULL,
	[Username] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[users] ON
INSERT [dbo].[users] ([Id], [FullName], [Email], [Role], [Password], [Username]) VALUES (9, N'Minh', N'minh@gmail.com', 0, N'21232F297A57A5A743894A0E4A801FC3', N'admin')
SET IDENTITY_INSERT [dbo].[users] OFF
/****** Object:  Table [dbo].[payment_method]    Script Date: 10/08/2022 09:18:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[payment_method](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[payment_method] ON
INSERT [dbo].[payment_method] ([Id], [Name]) VALUES (1, N'MasterCard')
INSERT [dbo].[payment_method] ([Id], [Name]) VALUES (2, N'Visa')
INSERT [dbo].[payment_method] ([Id], [Name]) VALUES (3, N'ATM')
INSERT [dbo].[payment_method] ([Id], [Name]) VALUES (4, N'Paypal')
SET IDENTITY_INSERT [dbo].[payment_method] OFF
/****** Object:  Table [dbo].[artist]    Script Date: 10/08/2022 09:18:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[artist](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Certificate] [varchar](100) NULL,
	[Description] [varchar](100) NULL,
	[Style] [varchar](100) NULL,
	[Expire_date] [varchar](255) NULL,
	[Cus_id] [int] NOT NULL,
	[Address] [nvarchar](max) NULL,
	[City] [nvarchar](max) NULL,
	[Country] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[artist] ON
INSERT [dbo].[artist] ([Id], [Certificate], [Description], [Style], [Expire_date], [Cus_id], [Address], [City], [Country]) VALUES (2, N'Bang chung nhan hoa si hang 3', N'Co nhieu giai thuong trong nuoc va quoc te', N'Free', N'9/14/2023 4:13:26 PM', 10, N'113 Tokyo', N'Tokyo', N'Japan')
INSERT [dbo].[artist] ([Id], [Certificate], [Description], [Style], [Expire_date], [Cus_id], [Address], [City], [Country]) VALUES (3, N'Hoa si hang 3 duoc cap nam 2022', N'Co nhieu giai thuong danh gia trong va ngoai nuoc', N'Hai huoc', N'10/14/2023 4:55:32 PM', 2, N'914 N Crescent Dr', N'Los Angeles', N'USA')
INSERT [dbo].[artist] ([Id], [Certificate], [Description], [Style], [Expire_date], [Cus_id], [Address], [City], [Country]) VALUES (4, N'Bang chung nhan hoa si hang 1', N'Tham gia nhieu chuong trinh cho hoa si chuyen nghiep', N'Don gian', N'9/14/2023 7:08:11 PM', 3, N'104 Hoan Kiem', N'Ha Noi', N'VN')
INSERT [dbo].[artist] ([Id], [Certificate], [Description], [Style], [Expire_date], [Cus_id], [Address], [City], [Country]) VALUES (5, N'khong co', NULL, N'Kinh di', N'9/14/2023 8:02:42 PM', 4, N'14267 Daemyeong 6(yuk) dong', N'Daegu', N'Korea')
INSERT [dbo].[artist] ([Id], [Certificate], [Description], [Style], [Expire_date], [Cus_id], [Address], [City], [Country]) VALUES (6, N'hoa si hang nhat nam 2020', N'danh giai 3 o giai cho hoa si co buc tranh u am nhat', N'Crazy', N'9/14/2023 8:30:02 PM', 1, N'Wu Hou Qu 8 4', N'Thanh Do', N'China')
INSERT [dbo].[artist] ([Id], [Certificate], [Description], [Style], [Expire_date], [Cus_id], [Address], [City], [Country]) VALUES (7, N'Khong co', N'Khong co', N'free', N'9/15/2023 9:07:46 AM', 5, N'99 Ong Cao Thang', N'HCM', N'VN')
INSERT [dbo].[artist] ([Id], [Certificate], [Description], [Style], [Expire_date], [Cus_id], [Address], [City], [Country]) VALUES (8, NULL, NULL, NULL, N'9/15/2023 9:19:14 AM', 6, N'50 bang coc', N'Bang Kok', N'ThaiLan')
INSERT [dbo].[artist] ([Id], [Certificate], [Description], [Style], [Expire_date], [Cus_id], [Address], [City], [Country]) VALUES (9, NULL, NULL, NULL, N'9/22/2023 2:47:46 PM', 11, N'76 Thai Thinh', N'HN', N'VN')
SET IDENTITY_INSERT [dbo].[artist] OFF
/****** Object:  Table [dbo].[order_buy]    Script Date: 10/08/2022 09:18:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[order_buy](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Total_price] [float] NOT NULL,
	[Cus_id] [int] NOT NULL,
	[Date_start] [varchar](100) NULL,
	[status] [int] NOT NULL,
	[Payment_id] [int] NOT NULL,
	[Country_code] [nchar](10) NULL,
	[Zip_code] [nvarchar](50) NULL,
	[PhoneNumber] [nvarchar](50) NULL,
	[Address] [varchar](max) NULL,
	[City] [nvarchar](50) NULL,
	[Recipient] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[order_buy] ON
INSERT [dbo].[order_buy] ([Id], [Total_price], [Cus_id], [Date_start], [status], [Payment_id], [Country_code], [Zip_code], [PhoneNumber], [Address], [City], [Recipient]) VALUES (6, 203, 1, N'9/29/2022 10:16:58 AM', 0, 1, N'VN        ', N'118500', N'0765432', N'123 Hoan Kiem', N'HN', N'Loan')
INSERT [dbo].[order_buy] ([Id], [Total_price], [Cus_id], [Date_start], [status], [Payment_id], [Country_code], [Zip_code], [PhoneNumber], [Address], [City], [Recipient]) VALUES (7, 1069, 1, N'9/29/2022 10:32:24 AM', 0, 1, N'USA       ', N'91016', N'065432', N'113 Los Angeles Ave', N'California', N'Den Vau')
INSERT [dbo].[order_buy] ([Id], [Total_price], [Cus_id], [Date_start], [status], [Payment_id], [Country_code], [Zip_code], [PhoneNumber], [Address], [City], [Recipient]) VALUES (8, 70, 7, N'9/29/2022 10:38:57 AM', 1, 2, N'JP        ', N'1000000', N'0865432', N'113 Tokyo', N'Tokyo', N'Lien')
INSERT [dbo].[order_buy] ([Id], [Total_price], [Cus_id], [Date_start], [status], [Payment_id], [Country_code], [Zip_code], [PhoneNumber], [Address], [City], [Recipient]) VALUES (10, 0, 2, N'9/30/2022 5:43:08 PM', 0, 1, N'VN        ', N'1001398', N'0123123123', N'13 Hoan Kiem', N'HN', N'Lien')
INSERT [dbo].[order_buy] ([Id], [Total_price], [Cus_id], [Date_start], [status], [Payment_id], [Country_code], [Zip_code], [PhoneNumber], [Address], [City], [Recipient]) VALUES (11, 0, 2, N'10/1/2022 12:34:35 AM', 0, 1, N'VN        ', N'0123123', N'09123123', N'77 Hoan kiem', N'HN', N'Lien')
SET IDENTITY_INSERT [dbo].[order_buy] OFF
/****** Object:  Table [dbo].[artwork]    Script Date: 10/08/2022 09:18:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[artwork](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Title] [varchar](100) NULL,
	[Price] [float] NULL,
	[Description] [varchar](100) NULL,
	[Year] [varchar](100) NULL,
	[img_path] [varchar](100) NOT NULL,
	[artist_id] [int] NOT NULL,
	[cate_id] [int] NOT NULL,
	[status] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[artwork] ON
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (4, N'C', 80, N'C', N'2021', N'Upload/Artwork/ArtistTuan.jfif', 7, 1, 1)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (5, N'D', 70, N'D', N'2017', N'Upload/Artwork/ArtistHai.jfif', 8, 3, 1)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (7, N'con heo', 705, N'ABCD', N'2018', N'Upload/Artwork/ArtistMinh09_20_2022_02_18_42.jfif', 3, 4, 0)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (8, N'Tay meo', 187, N'tay meo beo bu', N'2019', N'Upload/Artwork/ArtistMinh09_17_2022_11_20_20.jfif', 3, 1, 1)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (9, N'Phong canh A', 400, N'Dep that', N'2016', N'Upload/Artwork/Artistthinh09_18_2022_08_23_34.jfif', 6, 1, 1)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (10, N'u am', 300, N'phong canh toi', N'2018', N'Upload/Artwork/Artistthinh09_19_2022_07_11_55.jfif', 6, 3, 1)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (11, N'Huyen bi', 123, N'chill', N'2004', N'Upload/Artwork/ArtistMinh09_20_2022_11_22_51.jpg', 3, 1, 1)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (12, N'Khong co gi', 200, N'Linh ta linh tinh', N'2018', N'Upload/Artwork/ArtistMinh Le Tien09_20_2022_08_52_09.jfif', 3, 3, 1)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (13, N'thien nhien trong xanh', 678, N'Mot buc ve the hien net dep tinh tuy', N'2016', N'Upload/Artwork/ArtistMinh Le Tien09_20_2022_08_54_42.jfif', 3, 3, 1)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (14, N'Binh lang', 869, N've dip covid 19', N'2021', N'Upload/Artwork/ArtistMinh Le Tien09_22_2022_09_30_47.jfif', 3, 2, 1)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (15, N'Hoa la', 745, N've linh tinh giai sau', N'2022', N'Upload/Artwork/ArtistMinh Le Tien09_22_2022_11_06_59.jfif', 3, 4, 1)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (16, N'Canh dep', 120, N'Phong canh dep', N'2014', N'Upload/Artwork/Artistthinh09_29_2022_10_40_02.jfif', 6, 2, 0)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (17, N'Nen tho', 300, N'Duoc ve vao 1 dem thanh tinh ', N'2018', N'Upload/Artwork/Artistthinh09_29_2022_10_40_52.jfif', 6, 4, 1)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (18, N'tranh dem huyen ao', 400, N've vao ban dem thanh vang', N'2021', N'Upload/Artwork/ArtistMinh Le Tien10_07_2022_10_34_52.jfif', 3, 1, 2)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (19, N'Tranh ngoi lang dem', 250, N'khong co gi', N'2012', N'Upload/Artwork/ArtistMinh Le Tien10_07_2022_10_35_33.jfif', 3, 1, 0)
INSERT [dbo].[artwork] ([Id], [Title], [Price], [Description], [Year], [img_path], [artist_id], [cate_id], [status]) VALUES (20, N'dem la lung', 430, N'dem vang hoang so ', N'2015', N'Upload/Artwork/ArtistMinh Le Tien10_07_2022_10_37_46.jfif', 3, 1, 0)
SET IDENTITY_INSERT [dbo].[artwork] OFF
/****** Object:  Table [dbo].[bid]    Script Date: 10/08/2022 09:18:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[bid](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Start_Price] [float] NOT NULL,
	[End_Price] [float] NOT NULL,
	[Art_id] [int] NOT NULL,
	[Date_start] [varchar](100) NULL,
	[Date_end] [varchar](100) NULL,
	[Status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[bid] ON
INSERT [dbo].[bid] ([Id], [Start_Price], [End_Price], [Art_id], [Date_start], [Date_end], [Status]) VALUES (1, 100, 800, 8, N'9/17/2022 11:53:59 PM', N'9/19/2022 11:53:59 PM', 1)
INSERT [dbo].[bid] ([Id], [Start_Price], [End_Price], [Art_id], [Date_start], [Date_end], [Status]) VALUES (2, 300, 900, 9, N'9/18/2022 8:24:11 PM', N'9/20/2022 8:24:11 PM', 1)
INSERT [dbo].[bid] ([Id], [Start_Price], [End_Price], [Art_id], [Date_start], [Date_end], [Status]) VALUES (3, 200, 0, 10, N'9/19/2022 7:12:22 PM', N'9/20/2022 7:12:22 PM', 3)
INSERT [dbo].[bid] ([Id], [Start_Price], [End_Price], [Art_id], [Date_start], [Date_end], [Status]) VALUES (4, 200, 300, 13, N'9/22/2022 10:09:03 AM', N'9/23/2022 10:09:03 AM', 1)
INSERT [dbo].[bid] ([Id], [Start_Price], [End_Price], [Art_id], [Date_start], [Date_end], [Status]) VALUES (5, 100, 0, 14, N'9/22/2022 10:09:03 AM', N'9/23/2022 10:09:03 AM', 0)
INSERT [dbo].[bid] ([Id], [Start_Price], [End_Price], [Art_id], [Date_start], [Date_end], [Status]) VALUES (6, 600, 800, 15, N'9/22/2022 11:07:31 AM', N'9/23/2022 11:07:31 AM', 1)
INSERT [dbo].[bid] ([Id], [Start_Price], [End_Price], [Art_id], [Date_start], [Date_end], [Status]) VALUES (7, 300, 1000, 10, N'9/29/2022 10:19:19 AM', N'9/30/2022 10:19:19 AM', 1)
INSERT [dbo].[bid] ([Id], [Start_Price], [End_Price], [Art_id], [Date_start], [Date_end], [Status]) VALUES (8, 50, 0, 7, N'9/29/2022 10:28:59 AM', N'9/30/2022 10:28:59 AM', 0)
INSERT [dbo].[bid] ([Id], [Start_Price], [End_Price], [Art_id], [Date_start], [Date_end], [Status]) VALUES (9, 200, 0, 18, N'10/7/2022 10:38:25 PM', N'10/8/2022 10:38:25 PM', 0)
SET IDENTITY_INSERT [dbo].[bid] OFF
/****** Object:  Table [dbo].[favorite_artwork]    Script Date: 10/08/2022 09:18:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[favorite_artwork](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Cus_id] [int] NULL,
	[Artwork_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[favorite_artwork] ON
INSERT [dbo].[favorite_artwork] ([Id], [Cus_id], [Artwork_id]) VALUES (1, 1, 4)
INSERT [dbo].[favorite_artwork] ([Id], [Cus_id], [Artwork_id]) VALUES (2, 1, 5)
INSERT [dbo].[favorite_artwork] ([Id], [Cus_id], [Artwork_id]) VALUES (3, 1, 7)
INSERT [dbo].[favorite_artwork] ([Id], [Cus_id], [Artwork_id]) VALUES (4, 1, 10)
INSERT [dbo].[favorite_artwork] ([Id], [Cus_id], [Artwork_id]) VALUES (6, 1, 8)
SET IDENTITY_INSERT [dbo].[favorite_artwork] OFF
/****** Object:  Table [dbo].[order_detail]    Script Date: 10/08/2022 09:18:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_detail](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Order_id] [int] NOT NULL,
	[Art_id] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[order_detail] ON
INSERT [dbo].[order_detail] ([Id], [Order_id], [Art_id], [Quantity]) VALUES (4, 6, 4, 1)
INSERT [dbo].[order_detail] ([Id], [Order_id], [Art_id], [Quantity]) VALUES (5, 6, 11, 1)
INSERT [dbo].[order_detail] ([Id], [Order_id], [Art_id], [Quantity]) VALUES (6, 7, 12, 1)
INSERT [dbo].[order_detail] ([Id], [Order_id], [Art_id], [Quantity]) VALUES (7, 7, 14, 1)
INSERT [dbo].[order_detail] ([Id], [Order_id], [Art_id], [Quantity]) VALUES (8, 8, 5, 1)
INSERT [dbo].[order_detail] ([Id], [Order_id], [Art_id], [Quantity]) VALUES (9, 11, 17, 1)
SET IDENTITY_INSERT [dbo].[order_detail] OFF
/****** Object:  Table [dbo].[Update_bidding]    Script Date: 10/08/2022 09:18:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Update_bidding](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Cus_id] [int] NULL,
	[Bid_id] [int] NULL,
	[Time_update] [varchar](255) NULL,
	[Amount] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Update_bidding] ON
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (1, 2, 1, N'9/18/2022 3:56:42 AM', N'120')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (2, 2, 1, N'9/18/2022 3:59:02 AM', N'150')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (3, 2, 1, N'9/18/2022 4:02:20 AM', N'200')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (4, 3, 1, N'9/18/2022 7:04:03 PM', N'300')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (5, 1, 2, N'9/19/2022 1:41:17 AM', N'400')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (6, 1, 1, N'9/19/2022 1:41:38 AM', N'150')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (7, 3, 1, N'9/19/2022 3:44:36 AM', N'400')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (8, 3, 1, N'9/19/2022 3:50:41 AM', N'250')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (9, 5, 1, N'9/19/2022 4:08:43 AM', N'500')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (10, 4, 1, N'9/19/2022 4:11:38 AM', N'800')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (11, 1, 2, N'9/19/2022 12:57:33 PM', N'900')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (12, 1, 2, N'9/19/2022 12:59:01 PM', N'1000')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (13, 2, 4, N'9/22/2022 10:09:24 AM', N'300')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (14, 1, 4, N'9/22/2022 10:09:50 AM', N'250')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (15, 1, 4, N'9/22/2022 10:10:44 AM', N'400')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (16, 2, 6, N'9/22/2022 11:09:17 AM', N'700')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (17, 7, 6, N'9/22/2022 11:10:26 AM', N'1000')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (18, 7, 6, N'9/22/2022 11:12:47 AM', N'2000')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (19, 11, 6, N'9/22/2022 11:13:45 AM', N'800')
INSERT [dbo].[Update_bidding] ([Id], [Cus_id], [Bid_id], [Time_update], [Amount]) VALUES (20, 13, 7, N'10/6/2022 2:05:13 PM', N'1000')
SET IDENTITY_INSERT [dbo].[Update_bidding] OFF
/****** Object:  ForeignKey [order_buy_ibfk_3]    Script Date: 10/08/2022 09:18:36 ******/
ALTER TABLE [dbo].[order_buy]  WITH CHECK ADD  CONSTRAINT [order_buy_ibfk_3] FOREIGN KEY([Payment_id])
REFERENCES [dbo].[payment_method] ([Id])
GO
ALTER TABLE [dbo].[order_buy] CHECK CONSTRAINT [order_buy_ibfk_3]
GO
/****** Object:  ForeignKey [artwork_ibfk_2]    Script Date: 10/08/2022 09:18:36 ******/
ALTER TABLE [dbo].[artwork]  WITH CHECK ADD  CONSTRAINT [artwork_ibfk_2] FOREIGN KEY([artist_id])
REFERENCES [dbo].[artist] ([Id])
GO
ALTER TABLE [dbo].[artwork] CHECK CONSTRAINT [artwork_ibfk_2]
GO
/****** Object:  ForeignKey [artwork_ibfk_4]    Script Date: 10/08/2022 09:18:36 ******/
ALTER TABLE [dbo].[artwork]  WITH CHECK ADD  CONSTRAINT [artwork_ibfk_4] FOREIGN KEY([cate_id])
REFERENCES [dbo].[category] ([Id])
GO
ALTER TABLE [dbo].[artwork] CHECK CONSTRAINT [artwork_ibfk_4]
GO
/****** Object:  ForeignKey [bid_ibfk_1]    Script Date: 10/08/2022 09:18:36 ******/
ALTER TABLE [dbo].[bid]  WITH CHECK ADD  CONSTRAINT [bid_ibfk_1] FOREIGN KEY([Art_id])
REFERENCES [dbo].[artwork] ([Id])
GO
ALTER TABLE [dbo].[bid] CHECK CONSTRAINT [bid_ibfk_1]
GO
/****** Object:  ForeignKey [favorite_artwork_ibfk_2]    Script Date: 10/08/2022 09:18:36 ******/
ALTER TABLE [dbo].[favorite_artwork]  WITH CHECK ADD  CONSTRAINT [favorite_artwork_ibfk_2] FOREIGN KEY([Artwork_id])
REFERENCES [dbo].[artwork] ([Id])
GO
ALTER TABLE [dbo].[favorite_artwork] CHECK CONSTRAINT [favorite_artwork_ibfk_2]
GO
/****** Object:  ForeignKey [order_detail_ibfk_1]    Script Date: 10/08/2022 09:18:36 ******/
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [order_detail_ibfk_1] FOREIGN KEY([Order_id])
REFERENCES [dbo].[order_buy] ([Id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [order_detail_ibfk_1]
GO
/****** Object:  ForeignKey [order_detail_ibfk_2]    Script Date: 10/08/2022 09:18:36 ******/
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [order_detail_ibfk_2] FOREIGN KEY([Art_id])
REFERENCES [dbo].[artwork] ([Id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [order_detail_ibfk_2]
GO
/****** Object:  ForeignKey [Update_bidding_ibfk_2]    Script Date: 10/08/2022 09:18:36 ******/
ALTER TABLE [dbo].[Update_bidding]  WITH CHECK ADD  CONSTRAINT [Update_bidding_ibfk_2] FOREIGN KEY([Bid_id])
REFERENCES [dbo].[bid] ([Id])
GO
ALTER TABLE [dbo].[Update_bidding] CHECK CONSTRAINT [Update_bidding_ibfk_2]
GO
