package be.ipl.blitz.usecasesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.blitz.daoImpl.GameDaoImpl;
import be.ipl.blitz.daoImpl.UserDaoImpl;
import be.ipl.blitz.domaine.Face;
import be.ipl.blitz.domaine.Game;
import be.ipl.blitz.domaine.Game.State;
import be.ipl.blitz.domaine.User;
import be.ipl.blitz.usecases.CardsUcc;
import be.ipl.blitz.usecases.GameUcc;

@Singleton
@Startup
public class GameUccImpl implements GameUcc {
	private Game game;
	@EJB
	private GameDaoImpl gameDao;
	@EJB
	private UserDaoImpl userDao;

	@EJB
	private CardsUcc cardsUcc;

	public GameUccImpl() {
	}

	@PostConstruct
	public void postconstruct() {
		System.out.println("GestionPartieImpl created");
	}

	@PreDestroy
	public void predestroy() {
		System.out.println("GestionPartieImpl destroyed");
	}

	public boolean joinGame(String gameName, String pseudo) {
		if (game != null && game.getState() == State.IN_PROGRESS) {
			return false;
		}
		if (game == null || game.getState() == State.OVER) {
			return false;
		}
		User player = userDao.search(pseudo);
		if (game.addPlayer(player)) {
			gameDao.update(game);
			return true;
		}
		return false;
	}

	@Override
	@Lock(LockType.READ)
	public List<String> listPlayers() {
		if (game == null) {
			return null;
		}
		List<User> users = game.getPlayers();
		List<String> pseudos = new ArrayList<String>();
		for (User u : users) {
			pseudos.add(u.getName());
		}
		return pseudos;
	}

	@Override
	public boolean startGame() {
		if (game == null || game.getState() != State.INITIAL) {
			return false;
		}
		game = gameDao.findById(game.getId());
		if (game.startGame()) {
			gameDao.update(game);
			return true;
		}
		return false;
	}

	public String getCurrentPlayer() {
		if (game == null) {
			return null;
		}
		User u = game.getCurrentUser();
		if (u == null) {
			return null;
		}
		return u.toString();
	}

	@Override
	public Set<Face> throwDice() {
		if (game == null)
			return null;
		game = gameDao.findById(game.getId());
		return game.throwDice();
	}

	@Override
	public boolean deleteDice(int num, int id) {
		if (game == null)
			return false;
		game = gameDao.findById(game.getId());
		return game.deleteDice(num,id);
	}

	@Override
	public boolean nextPlayer() {/*
									 * if (game== null) return false; game =
									 * gameDao.findById(game.getId()); return
									 * game.commencerTourSuivant();
									 */
		return false;
	}

	@Override
	public String winner() {/*
							 * if (game == null) return null; game =
							 * gameDao.findById(game.getId()); User u =
							 * game.estVainqueur(); if (u == null) return null;
							 * return u.getName();
							 */
		return null;
	}

	@Override
	public boolean isOver() {/*
								 * if (game == null) return true; game =
								 * gameDao.findById(game.getId()); return
								 * game.getEtat() == Etat.FINIE;
								 */
		return false;
	}

	@Override
	public void cancelGame() {/*
								 * if (game != null) game.annuler();
								 */
	}

	@Override
	public boolean createGame(String gameName) {
		if (game != null) {
			return false;
		}
		this.game = new Game(gameName);
		game = gameDao.save(game);

		cardsUcc.shuffleDeck();
		return true;
	}

	@Override
	@Lock(LockType.READ)
	public State getState() {
		if (game == null) {
			return null;
		}
		return game.getState();
	}

	@Override
	public Game getCurrentGame() {
		return this.game;
	}
}
