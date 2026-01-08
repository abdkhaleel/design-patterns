

interface PlayerState {
    void pause(MediaPlayer player);
    void stop(MediaPlayer player);
    void play(MediaPlayer player);
}

class MediaPlayer {
    private PlayerState state;

    public MediaPlayer () {
        this.state = new StoppedState();
    }

    public void setState (PlayerState state) {
        this.state = state;
    }

    void play () {
        state.play(this);
    }

    void pause () {
        state.pause(this);
    }

    void stop () {
        state.stop(this);
    }
}

class PausedState implements PlayerState {
    @Override
    public void pause (MediaPlayer player) {
        System.err.println("Already Paused");
    }

    @Override
    public void play (MediaPlayer player) {
        System.out.println("Resuming Play");
        player.setState(new PlayingState());
    }

    @Override
    public void stop (MediaPlayer player) {
        System.out.println("Stopped");
        player.setState(new StoppedState());
    }
}

class PlayingState implements PlayerState {
    @Override
    public void pause (MediaPlayer player) {
        System.out.println("Paused");
        player.setState(new PausedState());
    }

    @Override
    public void play (MediaPlayer player) {
        System.err.println("Already Playing");
    }

    @Override
    public void stop (MediaPlayer player) {
        System.out.println("Stopped");
        player.setState(new StoppedState());
    }
}

class StoppedState implements PlayerState {
    @Override
    public void pause (MediaPlayer player) {
        System.err.println("Can't pause. Not playing");
    }

    @Override
    public void play (MediaPlayer player) {
        System.out.println("Start Playing");
        player.setState(new PlayingState());
    }

    @Override
    public void stop (MediaPlayer player) {
        System.err.println("Already Stopped");
    }
}

public class Main {
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer();

        player.pause();
        player.stop();
        player.play();
        player.pause();
        player.stop();
    }
}