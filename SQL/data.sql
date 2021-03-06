USE [Expeditor]
GO
SET IDENTITY_INSERT [dbo].[Etat] ON 

INSERT [dbo].[Etat] ([Identifiant], [Libelle]) VALUES (1, N'AFFECTABLE')
INSERT [dbo].[Etat] ([Identifiant], [Libelle]) VALUES (2, N'AFFECTEE')
INSERT [dbo].[Etat] ([Identifiant], [Libelle]) VALUES (3, N'EXPEDIEE')
INSERT [dbo].[Etat] ([Identifiant], [Libelle]) VALUES (4, N'REAFFECTABLE')
SET IDENTITY_INSERT [dbo].[Etat] OFF
SET IDENTITY_INSERT [dbo].[Commande] ON 

INSERT [dbo].[Commande] ([Identifiant], [Date_Commande], [Nom_Client], [Adresse], [Etat_id]) VALUES (1, CAST(N'2012-12-12' AS Date), N'Michel', N'42 rue nowhere', 1)
INSERT [dbo].[Commande] ([Identifiant], [Date_Commande], [Nom_Client], [Adresse], [Etat_id]) VALUES (2, CAST(N'2013-12-12' AS Date), N'Michel', N'42 rue nowhere', 1)
INSERT [dbo].[Commande] ([Identifiant], [Date_Commande], [Nom_Client], [Adresse], [Etat_id]) VALUES (3, CAST(N'2014-12-12' AS Date), N'Michelle', N'44 rue machin', 1)
SET IDENTITY_INSERT [dbo].[Commande] OFF
SET IDENTITY_INSERT [dbo].[Utilisateurs] ON 

INSERT [dbo].[Utilisateurs] ([Identifiant], [Nom], [Login], [MotDePasse], [Archive]) VALUES (1, N'test', N'test', N'test', 1)
INSERT [dbo].[Utilisateurs] ([Identifiant], [Nom], [Login], [MotDePasse], [Archive]) VALUES (2, N'Jean Mi', N'mi', N'jean', 0)
INSERT [dbo].[Utilisateurs] ([Identifiant], [Nom], [Login], [MotDePasse], [Archive]) VALUES (3, N'Rick', N'morty', N'morty', 0)
SET IDENTITY_INSERT [dbo].[Utilisateurs] OFF
SET IDENTITY_INSERT [dbo].[Articles] ON 

INSERT [dbo].[Articles] ([Identifiant], [Libelle], [Poids], [Archive]) VALUES (1, N'Disque dur', 150, 0)
INSERT [dbo].[Articles] ([Identifiant], [Libelle], [Poids], [Archive]) VALUES (2, N'Crate graphique', 100, 0)
INSERT [dbo].[Articles] ([Identifiant], [Libelle], [Poids], [Archive]) VALUES (3, N'Carte mère ', 200, 0)
INSERT [dbo].[Articles] ([Identifiant], [Libelle], [Poids], [Archive]) VALUES (4, N'Alimentation', 300, 0)
SET IDENTITY_INSERT [dbo].[Articles] OFF
INSERT [dbo].[Commande_Article] ([Article_id], [Commande_id], [Quantite_carton], [Quantite_commandee]) VALUES (1, 1, 0, 2)
INSERT [dbo].[Commande_Article] ([Article_id], [Commande_id], [Quantite_carton], [Quantite_commandee]) VALUES (1, 2, 0, 1)
INSERT [dbo].[Commande_Article] ([Article_id], [Commande_id], [Quantite_carton], [Quantite_commandee]) VALUES (2, 1, 0, 2)
INSERT [dbo].[Commande_Article] ([Article_id], [Commande_id], [Quantite_carton], [Quantite_commandee]) VALUES (2, 2, 0, 2)
INSERT [dbo].[Commande_Article] ([Article_id], [Commande_id], [Quantite_carton], [Quantite_commandee]) VALUES (3, 1, 0, 1)
INSERT [dbo].[Commande_Article] ([Article_id], [Commande_id], [Quantite_carton], [Quantite_commandee]) VALUES (3, 2, 0, 1)
INSERT [dbo].[Commande_Article] ([Article_id], [Commande_id], [Quantite_carton], [Quantite_commandee]) VALUES (4, 1, 0, 1)
INSERT [dbo].[Commande_Article] ([Article_id], [Commande_id], [Quantite_carton], [Quantite_commandee]) VALUES (4, 2, 0, 2)
INSERT [dbo].[Commande_Article] ([Article_id], [Commande_id], [Quantite_carton], [Quantite_commandee]) VALUES (4, 3, 2, 15)
INSERT [dbo].[Droits] ([Login], [Libelle]) VALUES (N'mi', N'manager')
INSERT [dbo].[Droits] ([Login], [Libelle]) VALUES (N'morty', N'employe')
INSERT [dbo].[Droits] ([Login], [Libelle]) VALUES (N'test', N'employe')
