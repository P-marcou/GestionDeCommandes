package dal.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dal.AccesBase;
import dal.IArticleDAL;
import dto.LigneCommandeArticle;
import model.Article;
import util.ArticlesCouple;

public class ArticleDAL implements IArticleDAL {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2091775112324635896L;

	private final String SELECT_BY_ID = "SELECT Identifiant, Libelle, Poids " + "FROM Articles WHERE Identifiant = ? AND Archive = 0";

	private final String SELECT_BY_LIBELLE = "SELECT Identifiant, Libelle, Poids " + "FROM Articles WHERE Libelle = ? and Archive = 0";

	private final String SELECT_ALL = "SELECT Identifiant, Libelle, Poids " + "FROM Articles WHERE Archive = 0";

	private final String INSERT = "INSERT INTO Articles VALUES(?,?,?)";

	private final String UPDATE = "UPDATE Articles SET Libelle = ?, Poids = ? " + "WHERE Identifiant = ?";

	private final String DELETE = "UPDATE Articles SET Archive = 1 WHERE Identifiant = ?";
	
	private final String SELECT_ARTICLES_COMMANDE = "SELECT TOP 1 a.Identifiant ,ca.Commande_id ,ca.Quantite_carton ,ca.Quantite_commandee ,a.Libelle ,a.Poids  FROM Commande_Article ca INNER JOIN Commande c ON ca.Commande_id = c.Identifiant  INNER JOIN Articles a ON ca.Article_id = a.Identifiant WHERE c.Identifiant=? ORDER BY a.Identifiant";

	ArticleDAL() {

	}

	@Override
	public Article getOneByID(int id) {
		Article retour = null;
		Connection cnx = null;
		try {
			cnx = AccesBase.getConnection();
			PreparedStatement requete = cnx.prepareStatement(SELECT_BY_ID);
			requete.setInt(1, id);
			ResultSet resultat = requete.executeQuery();
			while (resultat.next()) {
				int identifiant = resultat.getInt("Identifiant");
				String libelle = resultat.getString("Libelle");
				int poids = resultat.getInt("Poids");
				retour = new Article(identifiant, libelle, poids);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (cnx != null && !cnx.isClosed()) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retour;
	}

	@Override
	public Article getOneByLibelle(String lbl) {
		Article retour = null;
		Connection cnx = null;
		try {
			cnx = AccesBase.getConnection();
			PreparedStatement requete = cnx.prepareStatement(SELECT_BY_LIBELLE);
			requete.setString(1, lbl);
			ResultSet resultat = requete.executeQuery();
			while (resultat.next()) {
				int identifiant = resultat.getInt("Identifiant");
				String libelle = resultat.getString("Libelle");
				int poids = resultat.getInt("Poids");
				retour = new Article(identifiant, libelle, poids);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (cnx != null && !cnx.isClosed()) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retour;
	}

	@Override
	public List<Article> getAll() {
		List<Article> retour = null;
		Connection cnx = null;
		try {
			cnx = AccesBase.getConnection();
			PreparedStatement requete = cnx.prepareStatement(SELECT_ALL);
			ResultSet resultat = requete.executeQuery();
			retour = new ArrayList<>();
			while (resultat.next()) {
				int identifiant = resultat.getInt("Identifiant");
				String libelle = resultat.getString("Libelle");
				int poids = resultat.getInt("Poids");
				Article u = new Article(identifiant, libelle, poids);
				retour.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (cnx != null && !cnx.isClosed()) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retour;
	}

	@Override
	public Article insert(Article obj) {
		Connection cnx = null;
		try {
			if (obj != null) {
				cnx = AccesBase.getConnection();
				PreparedStatement requete = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				requete.setString(1, obj.getLibelle());
				requete.setInt(2, obj.getPoids());
				requete.setByte(3, (byte) (obj.isArchive() ? 1 : 0));
				requete.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (cnx != null && !cnx.isClosed()) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	@Override
	public Article update(Article obj) {
		Connection cnx = null;
		try {
			if (obj != null) {
				cnx = AccesBase.getConnection();
				PreparedStatement requete = cnx.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
				requete.setString(1, obj.getLibelle());
				requete.setInt(2, obj.getPoids());
				requete.setInt(3, obj.getIdentifiant());
				requete.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (cnx != null && !cnx.isClosed()) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	@Override
	public void delete(int identifiant) {
		Connection cnx = null;
		try {
			cnx = AccesBase.getConnection();
			PreparedStatement requete = cnx.prepareStatement(DELETE);
			requete.setInt(1, identifiant);
			requete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (cnx != null && !cnx.isClosed()) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArticlesCouple getArticlesByCommande(int idCommande) {
		ArticlesCouple retour = null;
		List<Article> articles = new ArrayList<Article>();
		List<LigneCommandeArticle> lignearticles = new ArrayList<LigneCommandeArticle>();
		Connection cnx = null;
		try {
			cnx = AccesBase.getConnection();
			PreparedStatement requete = cnx.prepareStatement(SELECT_ARTICLES_COMMANDE);
			requete.setInt(1, idCommande);
			ResultSet resultat = requete.executeQuery();
			while (resultat.next()) {				
				int identifiant = resultat.getInt("Identifiant");
				int commande_id = resultat.getInt("Commande_id");
				int Quantite_carton = resultat.getInt("Quantite_carton");
				int Quantite_commandee = resultat.getInt("Quantite_commandee");
				String libelle = resultat.getString("Libelle");
				int poids = resultat.getInt("Poids");
				
				articles.add(new Article(identifiant, libelle, poids));
				lignearticles.add(new LigneCommandeArticle(identifiant, commande_id, Quantite_commandee, Quantite_carton));
			}
			retour = new ArticlesCouple(articles, lignearticles);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (cnx != null && !cnx.isClosed()) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retour;
	}
	

}
