import java.util.ArrayList;
import java.util.List;

public abstract class Subject{
    private List<Observer> observers = new ArrayList<>();
    public void attach(Observer observer){
        observers.add(observer);
    }
    public void notifyObservers(String tweet){
        for(Observer observer: observers){
            observer.update(this, tweet);
        }
    }

}
