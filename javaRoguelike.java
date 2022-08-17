import java.lang.Math;
import java.lang.Thread;
import java.util.Scanner;

public class Main
{
    public static int s_y = 1;
    public static int s_x = 1;
    public char next_space = ' ';
    public char playr = '@';
    public float playr_hp = 100.0f;
    public int playr_ac = 12;
    public float speed = 1.5f;
    public int playr_lvl = 1;
    public int playr_xp = 0;
    public float py = 0.0f;
    public float px = 0.0f;
    public boolean playr_alive = true;
    public String playr_name;
    public char dungeonMap[][] = { // 40x20, this map is user-editable, but don't edit the beginning
        {'#','#','#','#','#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {'$',' ',' ',' ',' ','$',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {'#','#','#','#','#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
    };

    public boolean playr_collision(char wasd)
    { boolean c = false; // false by default, no collision by default
        switch (wasd) {
        case 'w': if (dungeonMap[(int)px][(int)py-1] == '#')
                  {
                      c = true;
                  }
                  else if (dungeonMap[(int)px][(int)py-1] == '$')
                  {
                      c = true;
                  }
                  else {
                      c = false;
                  }
                  break;
        case 'a': if (dungeonMap[(int)px-1][(int)py] == '#')
                  {
                      c = true;
                  }
                  else if (dungeonMap[(int)px-1][(int)py-1] == '$')
                  {
                      c = true;
                  }
                  else {
                      c = false;
                  }
                  break;
        case 's': if (dungeonMap[(int)px][(int)py+1] == '#')
                  {
                      c = true;
                  }
                  else if (dungeonMap[(int)px][(int)py+1] == '$')
                  {
                      c = true;
                  }
                  else {
                      c = false;
                  }
                  break;
        case 'd': if (dungeonMap[(int)px+1][(int)py] == '#')
                  {
                      c = true;
                  }
                  else if (dungeonMap[(int)px+1][(int)py] == '$')
                  {
                      c = true;
                  }
                  else {
                      c = false;
                  }
                  break;
        };
        return c;
    }
    public void playr_door()
    {
        // function i will write later
    }
    public void playr_move(char wasd)
    {
        boolean cc = playr_collision(wasd);
        boolean collision_true = cc == true;
        if (collision_true)
        {
            return;
        }
        else {
            if (wasd == 'w')
            {
                next_space = dungeonMap[(int)px][(int)py-1];
                py -= (1.0f * speed);
                dungeonMap[(int)px][(int)py] = playr;
            }
            else if (wasd == 'a')
            {
                next_space = dungeonMap[(int)px-1][(int)py];
                px -= (1.0f * speed);
                dungeonMap[(int)px][(int)py] = playr;
            }
            else if (wasd == 's')
            {
                next_space = dungeonMap[(int)px][(int)py+1];
                py += (1.0f * speed);
                dungeonMap[(int)px][(int)py] = playr;
            }
            else if (wasd == 'd')
            {
                next_space = dungeonMap[(int)px+1][(int)py];
                px += (1.0f * speed);
                dungeonMap[(int)px][(int)py] = playr;
            }
        }
    }
    public void cmd_prompt()
    {
        try (Scanner input = new Scanner(System.in)) {
            char cmd = input.next().charAt(0);
            if (cmd == 'w' || cmd == 'a' || cmd == 's' || cmd == 'd')
            {
                playr_move(cmd);
            }
            else {
                System.out.println("\n\nERROR: Invalid Action/Command, Please Enter A Valid Command");
                // java threads are so fucking painful, this is vscode's own code
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                printScreen();
            }
        }
    }
    public void printScreen()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        for (int y = 0; y < 40; y++)
        {
            for (int x = 0; x < 20; x++)
            {
                System.out.print(dungeonMap[x][y]);
            }
            System.out.print('\n');
        }
        System.out.println("\n\nHP: "+(int)playr_hp+"\tAC: "+playr_ac+"\tLevel: "+playr_lvl+"\tXP:"+playr_xp);
        System.out.println("Name: "+playr_name);
        cmd_prompt();
    }
    public int diceRoll(int d, int m)
    { int die = 0;
        switch (d) {
        case 4: die = (int)(Math.random()*(4-1+1)+1) + m;
                break;
        case 6: die = (int)(Math.random()*(6-1+1)+1) + m;
                break;
        case 8: die = (int)(Math.random()*(8-1+1)+1) + m;
                break;
        case 10: die = (int)(Math.random()*(10-1+1)+1) + m;
                 break;
        case 12: die = (int)(Math.random()*(12-1+1)+1) + m;
                 break;
        case 20: die = (int)(Math.random()*(20-1+1)+1) + m;
                 break;
        };
        return die;
    }
    public static void main(String[] args)
    {
    }
};
