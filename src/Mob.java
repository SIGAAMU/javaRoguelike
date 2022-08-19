import java.lang.Math;

public class Mob {
    public String mob_ls[] = {
        "Zombie",
        "Vampire",
        "Orc",
        "Werewolf",
        "Slime",
        "Troll",
        "Imp",
        "Celestial being",
        "Witch",
        "Skeleton",
        "Angry Farmer",
    };
    static String mob_wpn[] = {
        "Bitch-Slap",
        "Blood drain",
        "Angry Hulk Smash",
        "Furry slash",
        "Absorption attack",
        "Three trolls from Clash Royale fury",
        "Devilish smile",
        "The Holy Grail(you should be dead)",
        "Potion of poison(hol up is this minecraft?)",
        "Arrow shot(how do you make this any better)",
        "PITCH FORK ATTACK!",
    };
    static int mob_hp[] = { // this array needs one more stat, only has 10 where there's 11 monsters
        60,
        70,
        65,
        70,
        40,
        55,
        25,
        65,
        60,
        100,
    };
    static int mob_dmg[] = {
        20,
        7,
        10,
        7,
        4,
        15,
        12,
        1,
        4,
        17,
        30,
    };
    static int mob_rng[] = {
        3,
        3,
        4,
        2,
        1,
        2,
        1,
        1,
        5,
        8,
        4,
    };
    // the maximum possibilty a mob has of moving
    static float mobMove_max[] = {
        40.5,
        53.2,
        37.5,
        47.3,
        27.2,
        39.5,
        45.5,
        15.5,
        32.5,
        43.5,
        46.5,

    };
    // the minimum possibility a mob has of moving
    static float mobMove_min[] = {
        20.5,
        37.5,
        17.5,
        30.5,
        10.5,
        20.3,
        22.25,
        5.5,
        17.25,
        21.35,
        27.35,

    };
    // the required number to be rolled for a mob to move
    static float mobMove_required[] = {
        27.5,
        43.5,
        21.25,
        32.5,
        11.5,
        25.25,
        30.5,
        8.5,
        27.5,
        35.5,
        35.5,

    };
    // if monster moves, this is their speed
    static int mob_speed[] = {
        10,
        6,
        3,
        5,
        2,
        7,
        6,
        1,
        4,
        3,
        15,  
    
    };
    static static float T_mobY = 0.0f;
    static static float T_mobX = 0.0f;
    public static String T_mobName;
    public static int T_mobHP;
    public static String T_mobWpn;
    public static int T_mobWpn_rng;
    public static int T_mobWpn_dmg;
    // this series of functions only allows 1 mob at a time, but meh
    public void Mob(String mobName)
    {
        T_mobName = mobName;
        getMob_stat();
    }
    public void getMob_stat()
    {
        for (int i = 0; i < mob_ls.length; i++)
        {
            if (mob_ls[i] == T_mobName)
            {
                T_mobWpn = mob_wpn[i];
                T_mobWpn_dmg = mob_dmg[i];
                T_mobWpn_rng = mob_rng[i];
            }
        }
    }
    public void moveMob()
    {
        for (int i = 0; i < mob_ls.length; i++)
        {
            if (mob_ls[i] == T_mobName)
            {
                float moveBool = (float)(Math.random()*(mobMove_max[i]-mobMove_min[i]+1)+mobMove_min[i]);
                if (moveBool >= mobMove_required[i])
                {
                    int randMove = (int)(Math.random()*(4-1+1)+1);
                    if (randMove == 1) // north/w
                    {
                        T_mobY -= (1.0f * mob_speed[i]);
                    }
                    else if (randMove == 2) // west/a
                    {
                        T_mobX -= (1.0f * mob_speed[i]);
                    }
                    else if (randMove == 3) // south/s
                    {
                        T_mobY += (1.0f * mob_speed[i]);
                    }
                    else if (randMove == 4) // east/d
                    {
                        T_mobX += (1.0f * mob_speed[i]);
                    }
                }
            }
        }
    }
};
