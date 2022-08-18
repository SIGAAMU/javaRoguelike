import java.util.Scanner;

public class Character
{
    Main map = new Main();
    public static int s_y = 1;
    public static int s_x = 1;
    public char next_space = ' ';
    public char playr = '@';
    public static float playr_hp = 100.0f;
    public int playr_ac = 12;
    public float speed = 1.5f;
    public int playr_lvl = 1;
    public int playr_xp = 0;
    public float py = 0.0f;
    public float px = 0.0f;
    public String playr_name;
    public boolean playrAlive()
    { boolean alive = playr_hp > 0;
        return alive;
    }
    public boolean playr_collision(char wasd)
    { boolean c = false; // false by default, no collision by default
        switch (wasd) {
        case 'w': if (map.dungeonMap[(int)px][(int)py-1] == '#')
                  {
                      c = true;
                  }
                  else if (map.dungeonMap[(int)px][(int)py-1] == '$')
                  {
                      c = true;
                  }
                  else {
                      c = false;
                  }
                  break;
        case 'a': if (map.dungeonMap[(int)px-1][(int)py] == '#')
                  {
                      c = true;
                  }
                  else if (map.dungeonMap[(int)px-1][(int)py-1] == '$')
                  {
                      c = true;
                  }
                  else {
                      c = false;
                  }
                  break;
        case 's': if (map.dungeonMap[(int)px][(int)py+1] == '#')
                  {
                      c = true;
                  }
                  else if (map.dungeonMap[(int)px][(int)py+1] == '$')
                  {
                      c = true;
                  }
                  else {
                      c = false;
                  }
                  break;
        case 'd': if (map.dungeonMap[(int)px+1][(int)py] == '#')
                  {
                      c = true;
                  }
                  else if (map.dungeonMap[(int)px+1][(int)py] == '$')
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
                next_space = map.dungeonMap[(int)px][(int)py-1];
                py -= (1.0f * speed);
                map.dungeonMap[(int)px][(int)py] = playr;
            }
            else if (wasd == 'a')
            {
                next_space = map.dungeonMap[(int)px-1][(int)py];
                px -= (1.0f * speed);
                map.dungeonMap[(int)px][(int)py] = playr;
            }
            else if (wasd == 's')
            {
                next_space = map.dungeonMap[(int)px][(int)py+1];
                py += (1.0f * speed);
                map.dungeonMap[(int)px][(int)py] = playr;
            }
            else if (wasd == 'd')
            {
                next_space = map.dungeonMap[(int)px+1][(int)py];
                px += (1.0f * speed);
                map.dungeonMap[(int)px][(int)py] = playr;
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
                map.printScreen();
            }
        }
    }
};
