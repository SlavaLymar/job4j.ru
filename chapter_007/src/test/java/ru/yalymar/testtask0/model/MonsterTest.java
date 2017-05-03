package ru.yalymar.testtask0.model;

import org.junit.Test;
import ru.yalymar.testtask0.field.Field;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

public class MonsterTest {

    Field field = new Field();

    @Test
    public void whenMonsterMoveShouldGetHimFromNextCell() throws InterruptedException {
        Monsters monsters = new Monsters(this.field);
        int[] startXY = new int[]{monsters.getMonsters()[0].getX(), monsters.getMonsters()[0].getY()};
        int[] finishXY = monsters.getMonsters()[0].createMove();
        System.out.println("start: "+startXY[0]+" , "+startXY[1]);
        System.out.println("finish: "+finishXY[0]+" , "+finishXY[1]);
        assertNull(this.field.getCells()[startXY[1]][startXY[0]].getE());
        assertNotNull(this.field.getCells()[finishXY[1]][finishXY[0]].getE());
    }
}