import java.util.Scanner;

public class Character
{
    Main map = new Main();
    public static int s_y = 1;
    public static int s_x = 1;
    public char next_space = ' ';
    public char playr = '@';
    public static String playr_wpn[] = {
        "Fist", // melee
        "Sword", // primary
        "Dagger", // secondary
    };
    public static int playr_hp = 100;
    public int playr_ac = 12;
    public float speed = 1.5f;
    public int playr_lvl = 1;
    public int playr_xp = 0;
    public float py = 0.0f;
    public float px = 0.0f;
    public String playr_name;
    public boolean playrAlive()
    static String temp_mobName;
    static String temp_mobWpn;
    static int temp_mobDmg;
    static int temp_mobRng;
    
    public boolean playrAlive()
    { boolean alive = playr_hp > 0;
        return alive;
    };
    public void playrTakeDmg(int dmg)
    {
        playr_hp -= dmg
        System.out.println("You take "+dmg+" damage!");
        System.out.println("You are now down to "+playr_hp+" health!");
    }
    public boolean playr_collision(char wasd)
    { boolean c = false; // false by default, no collision by default
        switch (wasd) {
        case 'w': if (map.dungeonMap[(int)px][(int)py-1] == '#')
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
                  else {
                      c = false;
                  }
                  break;
        case 's': if (map.dungeonMap[(int)px][(int)py+1] == '#')
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
                  else {
                      c = false;
                  }
                  break;
        };
        return c;
    }
    public boolean playr_door(char wasd)
    { boolean d = false;
        switch (wasd) {
        case 'w': if (map.dungeonMap[(int)px][(int)py+1] == '$')
                  {
                      d = true;
                  }
                  else {
                      d = false;
                  }
                  break;
        case 'a': if (map.dungeonMap[(int)px-1][(int)py] == '$')
                  {
                      d = true;
                  }
                  else {
                      d = false;
                  }
                  break;
        case 's': if (map.dungeonMap[(int)px][(int)py+1] == '$')
                  {
                      d = true;
                  }
                  else {
                      d = false;
                  }
                  break;
        case 'd': if (map.dungeonMap[(int)px+1][(int)py] == '$')
                  {
                      d = true;
                  }
                  else {
                      d = false;
                  }
                  break;
        };
    };
    public void playr_move(char wasd)
    {
        boolean cc = playr_collision(wasd);
        boolean collision_true = cc == true;
        boolean dc = playr_door(wasd);
        boolean door = dc == true;
        if (collision_true)
        {
            return;
        }
        else if (door)
        {
            Scanner useDoor = new Scanner(System.in);
            char doorOrgive_up = 'n';
            System.out.println("\nEnter o to open a door (direction based on your latest movement)");
            System.out.println("Enter n to not open the door");
            System.out.print("What Would You Like To Do? >");
            doorOrgive_up = useDoor.next().charAt(0);
            switch (doorOrgive_up) {
            case 'n': map.printScreen();
                      break;
            case 'o': System.out.println("You open the Door");
                      if (wasd == 'w')
                      {
                          map.dungeonMap[(int)px][(int)py-2] = playr;
                          py -= 2.0f;
                      }
                      else if (wasd == 'a')
                      {
                          map.dungeonMap[(int)px-2][(int)py] = playr;
                          px -= 2.0f;
                      }
                      else if (wasd == 's')
                      {
                          map.dungeonMap[(int)px][(int)py+2] = playr;
                          py += 2.0f;
                      }
                      else if (wasd == 'd')
                      {
                          map.dungeonMap[(int)px+2][(int)py] = playr;
                          px += 2.0f;
                      }
                      break;
            default: System.out.println("ERROR: Invalid Response");
                     map.printScreen();
                     break;
            };
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
    // really kinda static, but it shouldn't ever be accessed by any other class
    public void playr_combat()
    {
        String mobN = getMobName();
        Mob fight = new Mob();
        fight.T_mobName = mobN;
        temp_mobName = mobN;
        temp_mobDmg = fight.T_mobWpn_dmg;
        temp_mobRng = fight.T_mobWpn_rng;
        boolean exitCombat = false;
        while (fight.T_mobHP > 0 || !exitCombat)
        {
            Scanner atk = new Scanner(System.in);
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            Main map = new Main();
            for (int y = 0; y < 40; y++)
            {
                for (int x = 0; x < 20; x++)
                {
                    System.out.print(map.dungeonMap[x][y]);
                }
                System.out.print('\n');
            }
            System.out.print("\n\n");
            System.out.println("Would You Like To Attack The "+mobN+"? [y/n]");
            System.out.print(">");
            char combat = atk.next().charAt(0);
            if (combat == 'y')
            {
                System.out.println("<Weapons> (in inventory)");
                for (int i = 0; i < playr_wpn.length; i++)
                {
                    System.out.println(i+") "+playr_wpn[i]);
                }
                System.out.print("\nChoose An Attack >");
                int playr_atk = atk.nextInt();
                fight.mobTakeDmg(playr_wpn[playr_atk]);
            }
            else if (combat == 'n')
            {
                System.out.println("Ok Then...");
                map.printScreen();
            }
            else {
                System.out.println("ERROR: Invalid Command");
                map.printScreen();
            }
        };
    }
    public void cmd_prompt()
    {
        try (Scanner input = new Scanner(System.in)) {
            char cmd = input.next().charAt(0);
            if (cmd == 'w' || cmd == 'a' || cmd == 's' || cmd == 'd')
            {
                playr_move(cmd);
            }
            else if (cmd == 't')
            { boolean mobTrue = mobChecker();
                if (mobTrue == true)
                {
                    playr_combat();
                }
                else {
                    System.out.println("ERROR: Invalid Target/No Mob Present");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    map.printScreen();
                }
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
