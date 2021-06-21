package seabattle.server.controller.dbcontroller;

import seabattle.server.model.User;
import seabattle.shared.datatype.Pair;
import seabattle.shared.game.ScoreboardRecord;
import seabattle.shared.request.NewUser;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class UserController extends AbstractController {
    public int addUser(NewUser newUser) {
        User user = new User(newUser.getUsername(), newUser.getPassword(), newUser.getRegisterTime());
        for (User user1 : context.Users.all()) {
            if (user1.getUsername().equals(user.getUsername())) {
                return -1;
            }
        }
        context.Users.add(user);
        return 0;
    }

    public int getUserId(String username) {
        System.out.println("username is usercontroller " + username);
        for (User user : context.Users.all()) {
            if (user.getUsername().equals(username))
                return user.getId();
        }
        return -1;
    }

    public User getUser(String username) {
        return context.Users.get(getUserId(username));
    }

    public void addScore(String username, int val) {
        User user = context.Users.get(getUserId(username));
        if (val > 0) {
            user.setWin(user.getWin() + 1);
        } else {
            user.setLose(user.getLose() + 1);
        }
        user.setScore(user.getWin() - user.getLose());
        context.Users.update(user);
    }

    public void updateLastOnline(String username) {
        User user = getUser(username);
        user.setLastOnline(LocalDateTime.now());
        context.Users.update(user);
    }

    public ScoreboardRecord[] getScoreboard() {
        LinkedList<Pair<Integer, String>> scoreboard = new LinkedList<>();
        for (User user : context.Users.all()) {
            scoreboard.add(new Pair<>(user.getScore(), user.getUsername()));
        }
        Collections.sort(scoreboard, new Comparator<Pair<Integer, String>>() {
            @Override
            public int compare(Pair<Integer, String> t1, Pair<Integer, String> t2) {
                return t2.getFirst() - t1.getFirst();
            }
        });
        ScoreboardRecord[] scoreboardRecords = new ScoreboardRecord[context.Users.all().size()];
        for (int i = 0; i < scoreboardRecords.length; i++) {
            scoreboardRecords[i] = new ScoreboardRecord(
                    scoreboard.get(i).getSecond(),
                    checkOnline(getUser(scoreboard.get(i).getSecond()).getLastOnline()),
                    scoreboard.get(i).getFirst()
            );
        }
        return scoreboardRecords;
//        StringBuilder s = new StringBuilder();
//        for (Pair<Integer,String> pair : scoreboard) {
//            s.append(pair.getSecond()).append(" -> ").append(pair.getFirst()).append("\n");
//        }
//        return s.toString();
    }

    private boolean checkOnline(LocalDateTime time) {
        long delta = ChronoUnit.SECONDS.between(time, LocalDateTime.now());
        return delta < 30;
    }

    public void clearUserDB() {
        context.Users.clear();
    }
}
