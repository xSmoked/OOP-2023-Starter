package ie.tudublin;

import java.util.ArrayList;

public class Word {
    String word;
    ArrayList<Follow> follows = new ArrayList<Follow>();

    public Word(String word) {
        this.word = word;
    }

    public String toString() {
        String poem = "";
        for(Follow f : follows)
        {
            poem += " " + f.toString();
        }
        return poem;
    }

    public void findFollow(String word)
    {
        for(Follow f : follows)
        {
            if(f.getWord().equals(word))
            {
                System.out.println("In here");
                f.setCount(f.getCount() + 1);
                return;
            }
        }
        follows.add(new Follow(word, 1));
        return;
    }

    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public ArrayList<Follow> getFollows() {
        return follows;
    }
    public void setFollows(ArrayList<Follow> follows) {
        this.follows = follows;
    }
    
}
