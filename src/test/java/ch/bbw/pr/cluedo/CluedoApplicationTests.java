package ch.bbw.pr.cluedo;

import ch.bbw.pr.cluedo.logic.GameLogic;
import ch.bbw.pr.cluedo.model.Crime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class CluedoApplicationTests {

	private GameLogic gameLogic;
	private Crime suggestion;
	private Crime secret;

	@BeforeEach
	private void setupBeforeTest() {
		gameLogic = new GameLogic();
		suggestion = new Crime();
		secret = new Crime();
	}

	@Test
	void evaluateSuggestion() {
		int numberOfSuggestion = 0;
		int maxNumberOfSuggestions = 8;

		// setup secret

		secret.setActor(0);
		secret.setWeapon(0);
		secret.setScene(0);

		// return true is expected
		assertEquals(true, gameLogic.evaluateSuggestion(suggestion, secret, numberOfSuggestion, maxNumberOfSuggestions));
	}

	@Test
	void ActorWeaponSceneNotEqualThenReturnFalseAndHistory0() {
		int numberOfSuggestion = 0;
		int maxNumberOfSuggestions = 8;

		secret.setActor(0);
		secret.setWeapon(1);
		secret.setScene(2);

		suggestion.setActor(1);
		suggestion.setWeapon(0);
		suggestion.setScene(0);

		assertFalse(gameLogic.evaluateSuggestion(suggestion, secret, numberOfSuggestion, maxNumberOfSuggestions));
	}

	@Test
	void ActorEqualWeaponSceneNotEqualThenReturnFalseAndHistory1() {
		int numberOfSuggestion = 0;
		int maxNumberOfSuggestions = 8;

		secret.setActor(1);
		secret.setWeapon(1);
		secret.setScene(2);

		suggestion.setActor(1);
		suggestion.setWeapon(0);
		suggestion.setScene(0);

		assertFalse(gameLogic.evaluateSuggestion(suggestion, secret, numberOfSuggestion, maxNumberOfSuggestions));
	}

	@Test
	void WeaponEqualActorSceneNotEqualThenReturnFalseAndHistory1() {
		int numberOfSuggestion = 0;
		int maxNumberOfSuggestions = 8;

		secret.setActor(0);
		secret.setWeapon(1);
		secret.setScene(2);

		suggestion.setActor(1);
		suggestion.setWeapon(1);
		suggestion.setScene(0);

		assertFalse(gameLogic.evaluateSuggestion(suggestion, secret, numberOfSuggestion, maxNumberOfSuggestions));
	}

	@Test
	void SceneEqualWeaponSceneNotEqualThenReturnFalseAndHistory1() {
		int numberOfSuggestion = 0;
		int maxNumberOfSuggestions = 8;

		secret.setActor(0);
		secret.setWeapon(0);
		secret.setScene(2);

		suggestion.setActor(1);
		suggestion.setWeapon(1);
		suggestion.setScene(2);

		assertFalse(gameLogic.evaluateSuggestion(suggestion, secret, numberOfSuggestion, maxNumberOfSuggestions));
	}

	@Test
	void ActorWeaponEqualSceneNotEqualThenReturnFalseAndHistory2() {
		int numberOfSuggestion = 0;
		int maxNumberOfSuggestions = 8;

		secret.setActor(0);
		secret.setWeapon(1);
		secret.setScene(2);

		suggestion.setActor(0);
		suggestion.setWeapon(1);
		suggestion.setScene(0);

		assertFalse(gameLogic.evaluateSuggestion(suggestion, secret, numberOfSuggestion, maxNumberOfSuggestions));
	}

	@Test
	void ActorSceneEqualWeaponNotEqualThenReturnFalseAndHistory2() {
		int numberOfSuggestion = 0;
		int maxNumberOfSuggestions = 8;

		secret.setActor(0);
		secret.setWeapon(1);
		secret.setScene(2);

		suggestion.setActor(1);
		suggestion.setWeapon(0);
		suggestion.setScene(2);

		assertFalse(gameLogic.evaluateSuggestion(suggestion, secret, numberOfSuggestion, maxNumberOfSuggestions));
	}

	@Test
	void WeaponSceneEqualActorNotEqualThenReturnFalseAndHistory2() {
		int numberOfSuggestion = 0;
		int maxNumberOfSuggestions = 8;

		secret.setActor(0);
		secret.setWeapon(1);
		secret.setScene(2);

		suggestion.setActor(1);
		suggestion.setWeapon(1);
		suggestion.setScene(2);

		assertFalse(gameLogic.evaluateSuggestion(suggestion, secret, numberOfSuggestion, maxNumberOfSuggestions));
	}

	@Test
	void ActorWeaponSceneEqualThenReturnTrueAndHistoryWin() {
		int numberOfSuggestion = 0;
		int maxNumberOfSuggestions = 8;

		secret.setActor(1);
		secret.setWeapon(1);
		secret.setScene(2);

		suggestion.setActor(1);
		suggestion.setWeapon(1);
		suggestion.setScene(2);

		assertFalse(gameLogic.evaluateSuggestion(suggestion, secret, numberOfSuggestion, maxNumberOfSuggestions));
	}

	@Test
	void MaxNumberOfSuggestionReachedAndNotWinThenReturnFalseAndHistoryNoneLeft() {
		int numberOfSuggestion = 8;
		int maxNumberOfSuggestions = 8;

		secret.setActor(0);
		secret.setWeapon(1);
		secret.setScene(2);

		suggestion.setActor(1);
		suggestion.setWeapon(1);
		suggestion.setScene(0);

		assertFalse(gameLogic.evaluateSuggestion(suggestion, secret, numberOfSuggestion, maxNumberOfSuggestions));
	}
}

