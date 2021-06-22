package seabattle.shared.response;

import seabattle.shared.game.Board;

public interface ResponseHandler {
    void showMessage(String s);
    void updateBoard(Board board, int id);
    void checkNewUserResponse(NewUserResponse newUserResponse);
    void checkUserLoginResponse(UserLoginResponse userLoginResponse);
    void checkGetPlayerInfoResponse(GetPlayerInfoResponse getPlayerInfoResponse);
    void checkEndGameResponse(EndGame endGame);
    void checkGetScoreboardResponse(GetScoreboardResponse getScoreboardResponse);
    void checkGetSpectateListResponse(GetSpectateListResponse getSpectateListResponse);
}
