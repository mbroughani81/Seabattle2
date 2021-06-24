package seabattle.shared.request;

import seabattle.shared.response.Response;

public interface RequestHandler {
    Response startGame();
    Response getBoard(GetBoard getBoard);
    Response getSpectateBoard(GetSpectateBoard getSpectateBoard);
    Response clickOnBoard(ClickOnBoard clickOnBoard);
    Response addNewUser(NewUser newUser);
    Response loginUser(UserLogin userLogin);
    Response getPlayerInfoChecker(GetPlayerInfo getPlayerInfo);
    Response getScoreboard(GetScoreboard getScoreboard);
    Response updateLastSeen(UpdateLastSeen updateLastSeen);
    Response getSpectateList(GetSpectateList getSpectateList);
    Response getSpectateGame(GetSpectateGame getSpectateGame);
}
