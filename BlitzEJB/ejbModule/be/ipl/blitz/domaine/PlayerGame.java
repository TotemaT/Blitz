package be.ipl.blitz.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import be.ipl.blitz.usecasesImpl.CardsUccImpl;

@SuppressWarnings("serial")
@Entity
@Table(name = "PLAYERS_GAMES", schema = "BLITZ")
@IdClass(PlayerGamePK.class)
public class PlayerGame implements Serializable {

	@Id
	private int userId;
	@Id
	private int gameId;

	@Transient
	@EJB
	private CardsUccImpl cardUcc;

	@ManyToOne(cascade = CascadeType.ALL)
	// @PrimaryKeyJoinColumn(name = "USERID", referencedColumnName = "ID")
	@JoinColumn(name = "userId", updatable = false, insertable = false)
	private User user;
	@ManyToOne(cascade = CascadeType.ALL)
	// @PrimaryKeyJoinColumn(name = "GAMEID", referencedColumnName = "ID")
	@JoinColumn(name = "gameId", updatable = false, insertable = false)
	private Game game;

	@Transient
	private int nbDice = 0;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(schema = "BLITZ")
	private List<Die> dice;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(schema = "BLITZ")
	private List<Card> cards;

	public PlayerGame(User u, Game g) {
		userId = u.getId();
		gameId = g.getId();
		this.user = u;
		this.game = g;
		dice = new ArrayList<Die>();
		// instanciation d'un dé pour recuperer le nombre de dé par personne
		Die d = new Die();
		for (int i = 0; i < d.getNbByPlayer(); i++) {
			dice.add(new Die());
		}
	}

	public PlayerGame() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game g) {
		this.game = g;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public List<Die> getDice() {
		return dice;
	}

	public boolean removeDie() {
		if (dice.isEmpty()) {
			return false;
		}
		this.dice.remove(0);
		return true;
	}

	public void setDice(List<Die> dice) {
		this.dice = dice;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public Card removeCard(int index) {
		return cards.remove(index);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + gameId;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerGame other = (PlayerGame) obj;
		if (gameId != other.gameId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlayerGame [userId=" + userId + ", gameId=" + gameId + ", cardUcc=" + cardUcc + ", user=" + user
				+ ", game=" + game + ", nbDice=" + nbDice + ", dice=" + dice + ", cards=" + cards + "]";
	}

}
