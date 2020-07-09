package com.pnu.whatyoueat;

import java.util.Random;

public class FoodTable {
    public String[] menu = new String[37];

    public FoodTable()
    {   menu[0]="비빔밥";
        menu[1]="국수";
        menu[2]="칼국수";
        menu[3]="찜닭";
        menu[4]="제육볶음";
        menu[5]="김치찌개";
        menu[6]="부대찌개";
        menu[7]="순두부찌개";
        menu[8]="보쌈 and 족발";
        menu[9]="밀면 and 냉면";
        menu[10]="분식";
        menu[11]="국밥";
        menu[12]="닭갈비";
        menu[13]="철판구이 and 볶음밥";
        menu[14]="삼계탕";
        menu[15]="불고기";
        menu[16]="닭볶음탕";
        menu[17]="갈비찜";
        menu[18]="김치찜";
        menu[19]="치킨";
        menu[20]="짜장 and 짬뽕";
        menu[21]="볶음밥";
        menu[22]="탄탄멘";
        menu[23]="파스타";
        menu[24]="햄버거";
        menu[25]="피자";
        menu[26]="샌드위치 and 토스트";
        menu[27]="초밥";
        menu[28]="덮밥";
        menu[29]="우동";
        menu[30]="라멘";
        menu[31]="돈가스";
        menu[32]="오므라이스";
        menu[33]="알밥";
        menu[34]="케밥";
        menu[35]="카레";
        menu[36]="태국음식";
    }
    public int length(){
        return menu.length;
    }
}
