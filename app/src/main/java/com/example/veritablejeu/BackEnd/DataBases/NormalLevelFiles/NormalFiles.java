package com.example.veritablejeu.BackEnd.DataBases.NormalLevelFiles;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;

import java.util.Arrays;
import java.util.List;

public class NormalFiles {

    private static final String autor = "Slab Road";
    private static final long time = 0L;
    private static final int movesNumber = 0;
    private static int idNum = 1;

    @NonNull
    private static String getNextId() {
        String id = Integer.toString(idNum);
        idNum += 1;
        return id;
    }

    private static final LevelFile tuto1 = new LevelFile(
            getNextId(),
            "Tutorial - 1",
            autor,
            time,
            movesNumber,
            "a210b1cffffffd2d2d6p1e00000000ffffffo11fm110p231t122sb16000000s15077w0s15086w0s17065w0b0s15068w0s15069w0s15079w0s1a089w0s1251s15067w0s15085w0s15087w0s15075w0"
    );

    private static final LevelFile tuto2 = new LevelFile(
            getNextId(),
            "Tutorial - 2",
            autor,
            time,
            movesNumber,
            "a210b1cfffffed8d2d5p1e00000000ffffffo11fm110p238t122sb16000000s15076w0s17085w0b0s15068w0s15075w0s1a089w0s1251s15067w0s15077w0s1a069w0s1251s17065w0b0s15088w0s15087w0"
    );

    private static final LevelFile tuto3 = new LevelFile(
            getNextId(),
            "Tutorial - 3",
            autor,
            time,
            movesNumber,
            "a210b1cffffffccd3cdp1e00000000ffffffo11fm110p25kt122sb16000000s15065w0s1a088w14r110s1a085w14b110s1a067w14t110s15097w0s15068w0s15095w0s1a098w0s1251s1e087w18l110r110s15066w0s1a086w0s1251s1c077w14b110b0s1a096w14l110s1c076w14r110b0s15078w0s1a075w14b110"
    );

    private static final LevelFile tuto4 = new LevelFile(
            getNextId(),
            "Tutorial - 4",
            autor,
            time,
            movesNumber,
            "a210b1cffffffc8c9c7p1e00000000ffffffo11fm110p23pt122sb16000000s1a08aw0s1251s17086w0b0s1a089w14t11as15069w0s17066w0b0s1m06aw0s1e01c1988787at89s15068w0s15088w0s15067w0s15087w0s15078w0"
    );

    private static final LevelFile tuto5 = new LevelFile(
            getNextId(),
            "Tutorial - 5",
            autor,
            time,
            movesNumber,
            "a210b1cffffffcccfd0p1e00000000ffffffo11fm110p24xt122sb16000000s17085w0b0s15067w0s1m069w0s1e01c19877779t88s1a089w0s1251s17065w0b0s1a068w14t11as15066w0s15087w0s1a088w14t11as15086w0s150a6w0s1k0a7w0s1c01c1766a6t68s15096w0s15077w0"
    );

    private static final LevelFile tuto6 = new LevelFile(
            getNextId(),
            "Tutorial - 6",
            autor,
            time,
            movesNumber,
            "a210b1cffffffbac7c9p1e00000000ffffffo11fm110p25wt122sb16000000s15068w0s15099w0s1a077w14t11as17057w0b0s15098w0s15076w0s1i088w0s1a01c1578t77s1507aw0s17075w0b0s15058w0s1i09aw0s1a11c157ab79s15059w0s1a07bw0s1251s1k05aw0s1c11c177959b79s17097w0b0s1a079w14b11bs15078w0"
    );

    private static final LevelFile tuto7 = new LevelFile(
            getNextId(),
            "Tutorial - 7",
            autor,
            time,
            movesNumber,
            "a210b1cffffffc4c7c9p1e00000000ffffffo11fm110p26ct122sb16000000s1a075w0s1251s15086w0s17089w0b0s15088w0s17069w0b0s15076w0s1a077w14b11bs1a065w0s1251s15068w0s17079w0b0s1i098w0s1a11c1578b77s1a085w0s1251s1i096w0s1a11c1576b77s15078w0s15066w0s1i056w0s1a11c1576b77s1i058w0s1a11c1578b77"
    );

    private static final LevelFile tuto8 = new LevelFile(
            getNextId(),
            "Tutorial - 8",
            autor,
            time,
            movesNumber,
            "a210b1cffffffc3c9cdp1e00000000ffffffo11fm110p2a8t122sb16000000s15054w0s15076w0s15083w0s15084w0s170a3w0b0s1k064w0s1c22c1574t73c0s15067w0s15053w0s15065w0s17036w0b0s15063w0s15074w0s15075w0s1a073w14t11cs1g033w0s1801c13t35s1g0a7w0s1811c13ba4s17079w0b0s15072w0s1a035w14t11as15066w0s15078w0s15044w0s1g0a6w0s1811c13ba4s1a060w0s1251s15086w0s150a5w0s15095w0s1a0a4w14b11bs15056w0s15071w0s15061w0s15055w0s15085w0s15034w0s15068w0"
    );

    private static final LevelFile tutoFinal = new LevelFile(
            getNextId(),
            "Tutorial - Final",
            autor,
            time,
            movesNumber,
            "a210b1cffffffc2cdcdp1e00000000ffffffo11fm110p2p5t122sb16000000s1a098w14l110s1e095w18l110b110s1a085w14b110s1c046w14t110b0s1a0a5w14b11bs1n065w14b110s1a01c1564l94s150b8w0s150e7w0s1a097w14l11as150d8w0s1a055w14r110s1x096w14l110s1k11c17b6b7lc7c1576t76s150a6w0s1n0d6w14t110s1a01c15d4rb4s15047w0s1a094w14l11as150e4w0s150b7w0s15077w0s15057w0s1a045w0s1251s15044w0s1a0e6w14t110s1m063w0s1e11c19a58385ba5s150a3w0s1a033w0s1251s1c036w14t110b0s150e5w0s1a034w0s1251s1o0e3w0s1g11c1b7688e886t76s1a035w0s1251s150a4w0s150a7w0s15078w0s150d5w0s15067w0s1k068w0s1c01c177778l97s1a0b4w14r11as15088w0s15073w0s15074w0s150d7w0s17037w0b0s1e0c6w18t110l110s150b5w0s17048w0b0s17038w0b0s15086w0s1a0c8w14l110s150e8w0s15087w0s1a0c7w14l11bs1i066w0s1a11c1576t76s1k083w0s1c11c17a585ba5s150c4w0s1a0c5w14l110s1a043w0s1251s1e056w18t110r110s15064w0s150d4w0s1a053w14r110s150a8w0s15084w0s150d3w0s1a058w14r110s15054w0s1a0c3w14l110s15075w0s1p093w14l110s1c11c17c3c7lc7s150b3w0s1a0b6w14t110s1a076w14t11b"
    );

    private static final LevelFile lvl1 = new LevelFile(
            getNextId(),
            "Level 1",
            autor,
            time,
            movesNumber,
            "a210b1cfe00006f0200p1e00000000ffffffo11fm111p2kjt122sb16bd0000s1a020w0s1251s1a034w14t11as1a010w0s1251s1a033w14l11bs15038w0s1a036w14t110s1g008w0s1811c13t07s1703cw0b0s1i005w0s1a11c1525t26s1a028w14l110s1g03bw0s1801c13t3as15003w0s1a021w0s1251s1c00aw16t13ac0s1a004w14t11cs1n024w14t110s1a11c1523l33s15025w0s15012w0s15018w0s1a029w14l110s1i02bw0s1a11c151bt1as1g001w0s1823c13t04s1702cw0b0s1g01bw0s1811c13t1as1e026w18t11bl110s1c02aw16t13ac0s1a000w0s1251s1701cw0b0s1a032w14l110s1700bw0b0s1a017w14t11cs15037w0s15009w0s1a007w14t11bs15011w0s1g006w0s1811c13t07s1a01aw14t11bs1700cw0b0s15013w0s1e031w18t110l110s1n014w14t110s1a11c1513l33s1a030w0s1251s15002w0s15023w0s1a022w0s1231s15039w0s1g035w0s1801c13t34s1a03aw14t11as15016w0s1p027w14l110s1c23c0c1517t17s1i015w0s1a11c1525t26s1q019w0s1i01c1509t0ac1529t2a"
    );

    private static final LevelFile lvl2 = new LevelFile(
            getNextId(),
            "Level 2",
            autor,
            time,
            movesNumber,
            "a210b1c04a9fe0201c8p1e00000000ffffffo11fm112p3109t122rb1600005bs15030w0s15060w0s1a003w14t110s15043w0s1a072w14l110s150a5w0s1a0b3w0s1251s1a062w14t11as1e056w18l11ar110s1i021w0s1a11c1511t11s1g002w0s1801c13t05s1g0a3w0s1801c13ta6s1a094w14r11as1a010w14r110s1e055w18l11br110s17006w0b0s1a096w14t110s15070w0s1r093w18t110r110s1a11c1543t41s1l074w14t110s1801c13r94s1i061w0s1a11c1531t32s1a045w14t110s17036w0b0s1i001w0s1a11c1511t11s1a063w14t110s1t064w1cl110b110r110s1801c13t62s15042w0s1e075w18t110l110s1a073w14r110s1a012w14r110s150a4w0s1i083w0s1a11c1543t41s150a2w0s1e082w18l110r11cs1e086w18t110r11as1a0a6w14t11as1t035w14t110s1g11c13l55c1525t25s1a052w14t110s17016w0b0s1g014w0s1801c13t13s15092w0s15046w0s1c005w14t11ab0s1a011w14t11bs1i0b5w0s1a11c1585t85s1n022w14t110s1a23c13r82c0s1e054w18t11al110s15050w0s15004w0s1i080w0s1a01c1581l81s1e0a0w18b110r11as1r0b6w14t110s1e01c13r86c13l56s1e071w18t11al110s1a0a1w0s1251s150b0w0s15024w0s1a032w14t11bs15040w0s1e085w18t11br110s1i051w0s1a11c1531t32s1a0b2w0s1251s15066w0s1i065w0s1a01c1575t76s15033w0s15020w0s15031w0s1a0b1w0s1251s1e084w18t110r110s1e081w18t110l11as1a0b4w0s1251s1i034w0s1a01c1554t54s1c015w14t110b0s1i041w1ct11bl110r110s1n095w14t110s1a11c1585t85s1e053w18t110l110s1a090w14l11as15044w0s1a025w14t11bs1a013w14t11as15023w0s1n000w14b110s1a01c1570t71s1a076w14t11as1z091w18b110r110s1i01c1590ra0c1590l90s1q026w0s1i11c174546l55c13t25"
    );

    private static final LevelFile lvl3 = new LevelFile(
            getNextId(),
            "Level 3",
            autor,
            time,
            movesNumber,
            "a210b1c7d7f77010001p1e00000000ffffffo11fm110p3100t122sb16000000s1503cw0s15038w0s1500aw0s1503dw0s1i06aw0s1a11c151at1bs15060w0s1502cw0s15015w0s1a020w0s1251s1a030w0s1251s1a01dw14r110s15062w0s1a053w14t110s1a04bw14t11bs1501aw0s1e063w18t11al110s15052w0s1a017w14t11as1e065w18l110b11as15054w0s1g018w0s1811c13t1bs1a064w14l110s1m04cw0s1e11c13t4bc13r1cs15055w0s1g011w0s1811c13l41s1e01bw18t11br110s15039w0s1503aw0s15013w0s1a00bw14t110s15058w0s1i008w0s1a11c1505l45s15059w0s1g021w0s1811c13l41s15005w0s15019w0s1a040w14l110s1a010w0s1251s15068w0s1a024w14l11as15033w0s15066w0s15026w0s1i051w0s1a01c1541t43s1c06bw14t110b0s1706cw0b0s1k002w0s1c23c0c1552t5bs1i036w0s1a01c1516t17s1a048w14l110s1500dw0s15009w0s1l004w14t110s1801c13l24s1502dw0s1a028w14r11as1a046w14l110s1r027w18t110r110s1a01c1547t47s1n007w14t110s1a11c1505l45s1705dw0b0s1a029w14r110s1i069w0s1a01c1568r28s1a000w0s1251s1a05bw14t11cs1a042w14l110s1e043w18t11al110s15032w0s15016w0s15050w0s1704dw0b0s1g067w0s1801c13b65s1a049w14l11as15006w0s1a04aw14l110s1a03bw14t110s1v02bw14t110s1i11c154bt4bc152cr1cs1a037w14t110s1i023w0s1a01c1533b34s1a041w14l11bs15003w0s1a056w14r110s1a045w14l11bs1706dw0b0s1505aw0s1a025w14t110s1a057w14t110s1a01cw14r11bs15022w0s1a001w0s1251s1a02aw14r110s1505cw0s1e047w18t11al110s1g061w0s1801c13t63s15035w0s1n044w14l110s1a01c1564b65s15012w0s1a034w14b11as1a014w14t110s15031w0s1i00cw0s1a01c1509l49"
    );

    private static final LevelFile lvl4 = new LevelFile(
            getNextId(),
            "Level 4",
            autor,
            time,
            movesNumber,
            "a210b1cfb644ba700ffp1e00000000ffffffo11fm113p314ot122sb1690005as1i00bw0s1a11c1509l19s15007w0s1z050w18l110r110s1i01c1552r52c1554r44s1a05bw14r110s1a032w14l11bs1a03aw14t11bs1a066w14t110s1503bw0s1e014w18t110l11cs1a028w14l110s1i06ew0s1a11c153et3as17030w0b0s1a041w14t110s15039w0s15065w0s15056w0s1a067w0s1251s15034w0s1a01cw14t110s15027w0s1e02aw18t11bl110s1a069w0s1251s1a068w0s1251s1a04aw14t11as1t00cw14t110s1g25c192a2c5ab59c0s1a044w14r11as1e024w18l110r11as15061w0s1i018w0s1a11c1528t2as1e055w18l110b11bs17000w0b0s1e053w18b110r110s1a06cw14t110s1502ew0s1e017w18l110b110s1g012w0s1811c13l32s1r031w18t110l110s1a11c1537l47s1506dw0s1z016w18t110l110s1i23c1514l14c1513r43s1a025w14t110s15037w0s1a01aw14t110s1a05aw14r110s1a05dw14l11as1i008w0s1a11c1528t2as1501bw0s1503dw0s15062w0s15042w0s15006w0s1503cw0s1a026w14t110s15038w0s1j05cw18t110l110s1241s15021w0s1o04bw0s1g01c13l2bc154dl5ds1i060w0s1a11c1565r35s1n05ew14l110s1a11c153et3as1a06bw0s1251s1g002w0s1801c13t04s15009w0s1500dw0s1503ew0s1e019w18t110l11bs1504dw0s1a059w14b11cs1504cw0s15013w0s1501dw0s15001w0s1a051w14r110s15003w0s1501ew0s1n036w14t110s1a11c1537l47s1a048w14l11bs15063w0s17010w0b0s1a049w14l110s1o057w0s1g11c1558l48c13b55s1502cw0s1502dw0s1a02bw14l11as15064w0s1e043w18t110r11cs1i011w0s1a11c1512l32s1a052w14r11as1504ew0s15054w0s1a035w14r11bs15023w0s1t046w14l110s1g01c1544r24c13t4as1g005w0s1811c13r35s1a06aw0s1251s1m058w0s1e11c13l48c13b55s1n00aw14t110s1a11c1509l19s1a047w14l11bs1a045w14t110s1500ew0s17020w0b0s1a015w14l110s1a004w14t11as17040w0b0s15022w0s1e033w18t110l110s15029w0"
    );

    private static final LevelFile lvl5 = new LevelFile(
            getNextId(),
            "Level 5",
            autor,
            time,
            movesNumber,
            "a210b1c650063000100p1e00000000ffffffo11fm111p3144t122sb16bdbebcs1502cw0s1a020w14l11as15064w0s1a060w14l11as15039w0s1a018w14t11as1506cw0s1503aw0s1700cw0b0s15053w0s1n076w14t110s1a11c1575r55s15007w0s1a015w14t11bs15004w0s15041w0s15016w0s1507aw0s1n027w14l110s1a01c1517t18s1a04bw14r11cs15011w0s1a047w14l110s15013w0s1502aw0s1a061w14l110s15040w0s1i077w0s1a11c1575r55s15019w0s1n045w14l110s1a01c1543l43s1r028w18t110l110s1a11c1548t48s15029w0s1e044w18t110l11as1r042w18t110l110s1a01c1543l43s1a038w14t11as1r026w18l110b110s1a01c1516t18s1i074w0s1a01c1564t66s1a048w14t11bs1a069w14b110s1a043w14l11as1i003w0s1a11c1513t12s1n005w14t110s1a11c1515t15s1n057w14b110s1a11c1567t68s15065w0s1n079w14b110s1a11c1569t68s1a055w14r11bs1a036w14b110s1g073w0s1801c13l63s1a008w14t110s1c00aw14t110b0s1a056w14r110s15006w0s1a014w14r110s15075w0s1507bw0s1a000w0s1241s1501aw0s15017w0s15070w0s1a072w14t110s1r052w18t110r110s1a11c1562t62s1a059w14b110s15031w0s1a078w14t110s1a062w14t11bs1n023w14l110s1a11c1513t12s15010w0s15071w0s1506bw0s1a04cw14r110s1a066w14t11as1g024w0s1801c13l44s1503cw0s1a012w14t11bs1e068w18t11bl110s15034w0s1c01cw14r110b0s1505cw0s1n002w14t110s1a01c1532t32s15037w0s1a046w14l110s15001w0s1i051w0s1a11c1561t62s1a063w14l11as1700bw0b0s1505bw0s1a067w14l110s15030w0s15050w0s15049w0s1i058w0s1a11c1548t48s1v021w14l110s1i01c1520l20c1520l60s1c01bw14r110b0s1502bw0s15033w0s1507cw0s1a032w14t11as1506aw0s1n025w14l110s1a11c1515t15s1g03bw0s1821c13r4bs1a04aw14r110s1e054w18t110r110s1e022w18t110l110s1a05aw0s1255s1i009w0s1a01c1539t38s15035w0"
    );

    private static final LevelFile lvl6 = new LevelFile(
            getNextId(),
            "Level 6",
            autor,
            time,
            movesNumber,
            "a210b1cff7effad96ffp1e00000000ffffffo11fm111p312wt122sb16009db6s1i065w1ct11al110r110s1a004w14t110s15040w0s15077w0s15000w0s1a02bw14t11as1701cw0b0s15055w0s1a031w14l11as1a058w14l110s1r029w18t110l110s1a01c1539t39s15009w0s1703bw0b0s1a062w14t110s1i008w0s1a01c1507l67s15003w0s1505bw0s1f06bw14l110s1231s15028w0s15079w0s15010w0s15021w0s1i060w0s1a01c1562l42s1a014w14t110s1a075w0s1251s1a04bw14l110s1a043w14l110s1a035w14b110s15024w0s1k001w0s1c23c0c1511t16s15037w0s1n06aw14l110s1a11c157at76s1a03aw14b110s1a046w14t110s1a051w14t11bs1i045w0s1a01c1565t65s15020w0s1a025w14b110s1a005w14b110s1t04aw18t110l110s1c23c150at0ac0s15012w0s1505aw0s1a059w14l110s1a066w14l11bs1g006w0s1811c13l66s1c067w16l13ac0s1a048w14t110s1e044w18l110b110s15017w0s1i033w0s1a11c1553t51s15061w0s15019w0s15078w0s1a00aw14t11cs1n068w14l110s1a01c1518t1as1a03cw14r11as1a013w14r110s15047w0s1a050w14r110s1v030w14l110s1i01c1531l31c1532l32s1507cw0s15007w0s1700cw0b0s1e056w18t110l110s1a022w14b110s1a069w14l11as1g049w0s1801c13l69s1a073w0s1251s1e071w18t11al110s15034w0s1f054w14b110s1251s1e032w18t110l11as1n06cw14l110s1a11c157ct76s1i023w0s1a11c1553t51s1a076w14t11bs1e042w18t110l11as1g072w0s1801c13t71s1e039w18t11ar110s15002w0s1a057w14l110s15018w0s15015w0s1a063w0s1251s1a016w14t11cs15070w0s1e041w18t110l110s1504cw0s15036w0s1a02aw14l110s1e038w18t110l110s15011w0s1a01aw14t11as1700bw0b0s1505cw0s15053w0s1507bw0s1a074w0s1251s1701bw0b0s15064w0s1m02cw0s1e01c13t2bc13r3cs1507aw0s15027w0s1g026w0s1811c13l66s1a052w14t110"
    );

    private static final LevelFile lvl7 = new LevelFile(
            getNextId(),
            "Level 7",
            autor,
            time,
            movesNumber,
            "a210b1c8fc20000fefep1e00000000ffffffo11fm110p31ewt122sb16000000s1502aw0s1i031w0s1a01c1551t57s170a8w0b0s1i050w0s1a01c1570t72s1e08bw18t110l11as1i04bw0s1a11c155bt59s150a3w0s15005w0s1a022w14l11as15014w0s1o094w0s1g11c13l84c1534t33s15004w0s1a002w0s1251s1a036w14t110s1a096w14l110s15034w0s1506aw0s15081w0s15051w0s1a053w14b110s15044w0s1a095w14l110s1a03bw14l110s1n016w14l110s1a01c1517l27s1r045w18t110l110s1a11c1540l40s1e052w18t110l110s1505bw0s1a060w14l110s1a074w14t110s1e023w18t110l110s1g013w0s1801c13t15s1a01aw14t11bs1o0a4w0s1g11c1534t33c13l84s170a9w0b0s1501bw0s15099w0s1a03aw14l110s1a077w14t11cs1a000w0s1251s1509aw0s1i0a0w0s1a01c15a1l61s1a048w14l110s15058w0s1502bw0s1a049w14t110s1a075w14l110s170a5w0b0s1i007w0s1a11c1508l18s1e029w18t11al110s1a0abw14t11as1a018w14l11bs15012w0s1a082w14t110s1a07aw14r110s1a076w14t110s15068w0s1n069w14t110s1a11c1519t1as1a067w14t110s1a064w14l110s15030w0s1i043w1ct110l110b110s1a092w14t110s15008w0s1a019w14t110s15070w0s1i009w0s1a11c1508l18s1a001w0s1251s15042w0s1a065w14t110s15091w0s1a024w14l110s15097w0s1i089w0s1a01c158bl8bs150aaw0s1n087w14t110s1a11c1597t9bs170a7w0b0s1r047w18t110l110s1a23c1577t77s15054w0s1504aw0s1a059w14t11bs15071w0s1a066w14t11as15003w0s1g063w0s1801c13t66s1i090w0s1a11c1580t86s1a037w14t11as1a09bw14t11bs1a021w14l110s1a061w14l11as1a084w14l11bs1e072w18t11al110s1a085w14t110s1a055w14l110s1e026w18t110l110s1i046w0s1a11c1540l40s15006w0s1r079w18t110r110s1a11c1519t1as1a062w14t110s1a010w0s1251s15035w0s1n00aw14t110s1a01c15aatabs15093w0s1l025w14l110s1801c13t29s1505aw0s1508aw0s1a057w14t11as15083w0s1a040w14l11bs1i00bw0s1a01c153bt37s1i078w0s1a11c1598t9bs150a1w0s15088w0s1a033w14t11bs1n073w14l110s1a01c1572l22s1g080w0s1811c13t86s1a028w14l110s1i06bw0s1a11c155bt59s1e032w18t110l110s1a020w14l110s1a039w14l110s15038w0s1e017w18t110l110s1e015w18t11al110s1a056w14t110s1e027w18t110l11as15098w0s1507bw0s170a6w0b0s1a041w14l110s1a086w14t11bs1a011w0s1251s1a0a2w14t110"
    );

    private static final LevelFile lvl8 = new LevelFile(
            getNextId(),
            "Level 8",
            autor,
            time,
            movesNumber,
            "a210b1cfeffff7b8285p1e00000000ffffffo11fm111p311at122sb16292c2fs1n072w14t110s1a11c1562t62s15017w0s15067w0s1a044w14l11as15031w0s15004w0s15022w0s1a015w14b11bs1703bw0b0s1704bw0b0s1a078w14t11bs15061w0s1a008w14t110s15000w0s15037w0s1g010w0s1811c13b15s1e04aw18t110l110s1a047w14l11bs1a038w14t110s1i079w0s1a11c1569t68s1a00aw14t11as1i002w0s1a11c1512b15s1a023w14t11as1a076w14t110s1n052w14t110s1a11c1562t62s1a074w0s1251s15011w0s15024w0s1a054w0s1251s1i006w0s1a01c1526t28s15027w0s1a036w14t110s15065w0s1a064w0s1251s1a043w14l110s1a040w14l110s1a028w14t11as15077w0s15060w0s1i020w0s1a01c1521l41s1a018w14t110s1i057w0s1a11c1577t78s1a068w14t11bs1702bw0b0s1n045w14l110s1a11c1555t56s1n03aw14t110s1a01c155at5as1i035w0s1a11c1537l47s1705bw0b0s1507bw0s1g007w0s1801c13t0as15032w0s1e048w18t110l110s1701bw0b0s1506bw0s1a003w14t110s15021w0s1g075w0s1811c13t78s15029w0s1a056w14t11bs15034w0s1i050w0s1a01c1551l41s15051w0s15059w0s15069w0s1n02aw14t110s1a01c150at0as1a062w14t11bs1a073w0s1251s1a063w0s1251s1a058w14t110s15016w0s1a06aw14t110s15053w0s1a013w14t110s1a05aw14t11as1i071w0s1a11c1561t62s15014w0s1a026w14t110s15025w0s1i039w0s1a11c1537l47s15012w0s1n049w14l110s1a11c1547l47s1r046w18t110l110s1a11c1556t56s1n005w14b110s1a01c1525t28s15019w0s15030w0s1a041w14l11as1n066w14t110s1a11c1556t56s15001w0s1500bw0s1z042w18t110l110s1i01c1522t23c1544l44s15009w0s1i070w0s1a11c1560t62s1a01aw14t110s1a033w14t110s1n07aw14t110s1a11c156at68s15055w0"
    );

    private static final LevelFile lvl9 = new LevelFile(
            getNextId(),
            "Level 9",
            autor,
            time,
            movesNumber,
            "a210b1cffaf00010000p1e00000000ffffffo11fm113p310nt122sb16db8203s15005w0s1a020w14l11as15052w0s1702bw0b0s1n044w14l110s1a11c1542l42s1f062w14l110s1251s15017w0s1a040w14l110s1e046w18t110l110s1n009w14t110s1a11c1519t19s1i078w0s1a11c1568t69s1701aw0b0s1a019w14t11bs1i041w0s1a11c1542l42s1700bw0b0s1i032w0s1a11c1531r31s15030w0s15074w0s1g008w0s1811c13l48s1a031w14r11bs15015w0s15027w0s1a053w14t11as15067w0s15064w0s15028w0s1701bw0b0s15037w0s1l073w14t110s1801c13t76s1a072w0s1251s1507aw0s1a071w0s1251s1a013w14t110s1i012w0s1a11c1522t23s1505bw0s1u050w0s1m01c1551l61c13l20c13t53s1a04bw14l110s1a069w14t11bs1g021w0s1811c13r31s1i007w0s1a11c1527t26s15034w0s15014w0s1f060w14l110s1251s1n029w14t110s1a11c1519t19s1a026w14t11bs15024w0s1a056w14t110s15001w0s1n079w14t110s1a11c1569t69s1a023w14t11bs1a039w14t11cs15025w0s1a003w14t11cs1a04aw14l11as1a047w14l11as1506bw0s15075w0s1a016w14t110s1505aw0s1a000w0s1241s1a061w14l11as1a076w14t11as1a048w14l11bs15058w0s1e043w18t110l11as15038w0s1i03bw0s1a01c153al4as15068w0s1a042w14l11bs1i004w0s1a24c0c13t03s15018w0s15055w0s15051w0s15057w0s1r049w18t110l110s1a11c1548l48s1a063w14t110s1g054w0s1823c13t59s1a059w14t11cs1a070w0s1251s1a022w14l110s1506aw0s1n006w14t110s1a11c1526t26s1a036w14t110s1700aw0b0s1i035w0s1a01c1533l43s15065w0s1i002w0s1a11c1522t23s1a011w14r110s1507bw0s15010w0s15077w0s1n045w14l110s1a01c1547l47s1503aw0s1a033w14t110s1n066w14t110s1a23c1536t39s1502aw0"
    );

    private static final LevelFile lvl10 = new LevelFile(
            getNextId(),
            "Level 10",
            autor,
            time,
            movesNumber,
            "a210b1c000000000300p1e1fefdfe0ffffffo11tm112p319kt122cb16ee8bb1s15025w0s1508cw0s1g01ew0s1811c13r2es1c04aw14t110b0s15002w0s1a031w14l110s1f038w14l110s1251s1i001w0s1a11c1541t43s1a07bw14t110s1t016w14t110s1g01c13l36c1515l65s1n032w14l110s1a01c1512t13s1e02cw18t110l110s1c05aw14t110b0s1k007w0s1c23c1587t88c0s1n01dw14t110s1a11c151er2es1g041w0s1811c13t43s15005w0s1o000w0s1g01c13r20c1550t53s15089w0s1a04dw14t110s1e06bw18t110l110s1r033w18t110l110s1a11c1523t29s1a037w14l110s15010w0s1a029w14t11bs1i058w0s1a01c1578t76s1507dw0s1a020w14r11as15044w0s1a006w14t11as1a00dw14t11as1a02aw14r11as1508dw0s15055w0s15014w0s15030w0s1e067w18l110b110s1i04bw0s1a11c154cl3cs15052w0s15022w0s1a069w14l110s1a088w14t11cs1f00bw14t110s1241s1f039w14l110s1251s1g05cw0s1811c13l3cs15045w0s1g00aw0s1801c13r2as15012w0s1a065w14l11as1508aw0s1a059w0s1251s15024w0s15017w0s1504cw0s1a08bw14t11as1a086w14t110s1503ew0s1o07cw0s1g01c1b4d4e2d7et2bs1a076w14t11as1i079w0s1a01c1589t8bs1507aw0s1p05dw14t110s1c01c176a6dl6as1501bw0s1l023w14t110s1811c13t29s15087w0s1n019w14t110s1a11c1529t29s1n056w14t110s1a11c1546t46s15018w0s1a06aw14l11as15075w0s1501cw0s1a013w14t11as15042w0s15008w0s1a03dw14t110s15027w0s1c03bw14l110b0s1n003w14t110s1a01c1513t13s1508ew0s1a046w14t11bs1a068w14l110s1a02dw14r110s15015w0s1500cw0s15028w0s15011w0s1c03aw14t110b0s1a02ew14r11bs1e066w18t110l110s1a02bw14t11as1n009w14t110s1a11c1529t29s1a03cw14l11bs15057w0s15078w0s15051w0s1g00ew0s1801c13t0ds15050w0s15047w0s1a06dw14l110s1a06cw14l110s1i054w0s1a01c1504t06s1a048w0s1251s1a06ew14l110s1a026w14t110s1705bw0b0s1a034w14l110s15040w0s1a01aw14b110s1507ew0s1r036w18t110l11as1a11c1546t46s1a049w0s1251s1a053w14t11as15021w0s1504ew0s1a035w14l110s15004w0s15085w0s1a077w14b110s1a043w14t11bs1505ew0"
    );

    private static final LevelFile lvl11 = new LevelFile(
            getNextId(),
            "Level 11",
            autor,
            time,
            movesNumber,
            "a210b1c7dca7c000100p1e00000000ffffffo11fm110p31g3t122sb16008e3as1e045w18t110l11as15013w0s150a1w0s1a052w14l11cs15010w0s1a009w14t11as15076w0s1a09aw14l11as150a9w0s150b4w0s1i059w0s1a11c155bl7bs150b1w0s1g002w0s1801c13t04s1a088w14b110s1502aw0s15034w0s15066w0s1a086w14r11cs150b3w0s1a064w14t110s1a024w14l110s15075w0s1r049w18t110l110s1a01c1545l45s15044w0s15000w0s15031w0s15077w0s15005w0s15095w0s1a0b6w14t11cs1a085w14r110s1503aw0s15030w0s1701bw0b0s15067w0s15012w0s15042w0s15033w0s15083w0s1a020w14l11bs150b0w0s1a072w14t110s21009bw0s1s11c179484t84c1599l79c1593l93s15040w0s15055w0s150a3w0s1702bw0b0s15038w0s1a006w0s1231s1a025w14t110s1o062w0s1g23c13l52c15a2ta6s1a07bw14l11bs1a04aw14l11bs150b2w0s1a089w14r110s15026w0s1700bw0b0s1a081w0s1251s1a090w0s1251s15027w0s1a0a6w14t11cs150a5w0s1g037w0s1801c13t35s15073w0s15061w0s150abw0s15063w0s150a4w0s1a080w0s1251s1i0b9w0s1a01c15bal9as1y099w0s1q11c13l79c1593l93c179484t84s1a087w14r110s1505aw0s1a017w14l110s1a08bw14r110s15094w0s1a047w14l110s1506bw0s1506aw0s1l078w14b110s1811c13l98s1e084w18t11br110s1a048w14l110s1a053w14l110s15097w0s15032w0s1a0b8w14t11cs1700aw0b0s1i0a7w0s1a01c1557t54s1n029w14t110s1a01c1509t09s1i041w0s1a11c1540l50s1i03bw0s1a11c153al4as1a074w14t110s15008w0s150bbw0s1q0a0w0s1i23c15b0tb8c15b0tb6s1a07aw14l110s15056w0s1e098w18t110l11bs1a018w14l11as1n058w14b110s1a01c1518t19s1e054w18t11al110s15028w0s1a046w14l11cs15057w0s1a079w14l11bs1508aw0s1a004w14t11as1n023w14l110s1a23c1525l15s1a04bw14l110s1a019w14t11as15003w0s1f071w14l110s1251s1a035w14t11as1a082w14t110s1a051w14l110s150b5w0s1e015w18t110l11cs150a2w0s1n021w14l110s1a11c1520l20s150aaw0s1a014w14l110s15011w0s1i065w0s1a24c1566r86s1a092w14l110s1l070w14l110s1811c13l50s15036w0s1a093w14l11bs1n039w14t110s1a11c153al4as1a050w14l11bs1a0a8w14t110s15001w0s15060w0s150baw0s1i007w0s1a01c1508l18s1n022w14l110s1a11c1520l20s1g05bw0s1811c13l7bs1701aw0b0s1a096w14t110s150b7w0s15043w0s1l016w14l110s1823c13l46s1a091w0s1251s15069w0s1l068w14b110s1811c13l98"
    );

    private static final LevelFile tutorialGhost = new LevelFile(
            getNextId(),
            "Tutorial - Ghost",
            autor,
            time,
            movesNumber,
            "a210b1c780053000101p1e00000000ffffffo11fm112p28ht122sb16a9008fs15133w0s15124w0s15061w0s1a018w0s1251s15152w0s15008w0s15010w0s1a011w0s1261s15145w0s15165w0s17000w0b0s15050w0s1a114w14l110s15143w0s15155w0s15103w0s15105w0s15162w0s15131w0s1a051w0s1261s15001w0s15104w0s15060w0s15102w0s15007w0s15106w0s1a063w0s1261s15017w0s15123w0s15121w0s15141w0s15144w0s15164w0"
    );

    public static final List<LevelFile> LEVELS = Arrays.asList(
            tuto1,
            tuto2,
            tuto3,
            tuto4,
            tuto5,
            tuto6,
            tuto7,
            tuto8,
            tutoFinal,
            lvl1,
            lvl2,
            lvl3,
            lvl4,
            lvl5,
            lvl6,
            lvl7,
            lvl8,
            lvl9,
            lvl10,
            lvl11,
            tutorialGhost
    );

}
