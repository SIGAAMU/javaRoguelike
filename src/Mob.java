import java.lang.Math;

public class Mob {
    public String mob_ls[] = {

    };
    static int mob_wpn[] = {

    };
    // the mobMove array is the possibilities the specific mob has of moving every game loop
    static float mobMove_max[] = {

    };
    static float mobMove_min[] = {

    };
    static float mobMove_required[] = {

    };
    static float T_mobY = 0.0f;
    static float T_mobX = 0.0f;
    public String T_mobName;
    public String T_mobWpn;
    public int T_mobWpn_dmg;
    public void getMob_stat()
    {

    }
    public void moveMob()
    {
        for (int i = 0; i < mob_ls.length; i++)
        {
            if (mob_ls[i] == T_mobName)
            {
                T_mobY = (float)(Math.random()*(mobMove_max[i]-mobMove_min[i]+1)+mobMove_min[i]);
            }
        }
    }
};
