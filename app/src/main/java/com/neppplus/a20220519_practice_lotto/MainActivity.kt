package com.neppplus.a20220519_practice_lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // 컴퓨터가 뽑은 당첨번호를 6개 저장할 ArrayList 만들어 주자
    val mWinNumList = ArrayList<Int>()
    //텍스트 ArrayList
    val mWinNumViewList = ArrayList<TextView>()
    //보너스 숫자 저장용 멤버 변수 생성
    val bonusNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    fun setupEvents() { Btn1.setOnClickListener() {
        buyLotto()
    }


            }
    fun setValues() {

        mWinNumViewList.add(winNum1)
        mWinNumViewList.add(winNum2)
        mWinNumViewList.add(winNum3)
        mWinNumViewList.add(winNum4)
        mWinNumViewList.add(winNum5)
        mWinNumViewList.add(winNum6)

    }

    fun buyLotto() {
//        1. 로또 당첨 번호 6개 선정
        // 당첨 번호 새로 뽑기 전에 기존의 번호는 삭제하고 다시 뽑는다.
        // arrayList는 목록이 계속해서 누적되기 때문
        mWinNumList.clear()

        for ( i in 0..5) {
            // 중복되는 번호가 나오지 않을 떄 까지
            while (true) {
                val randomNum = (Math.random()*45 +1 ).toInt()
//                var isRepeat = true
//                for ( winNum in mWinNumList) {
//                    if (randomNum == winNum) {
//                        isRepeat = true
//                        break
//                    }
//                    mWinNumList[i] = randomNum
//                }

                if (!mWinNumList.contains(randomNum)){
                    mWinNumList.add(randomNum)
                    break
                }

            }

        }
        Log.d("당첨 번호",mWinNumList.toString())
//
//        2. 당첨 번호 정렬 (sort) -> 작은 수 - 큰 수 -> 텍스트뷰에 표현
        mWinNumList.sort()
        Log.d("당첨 번호",mWinNumList.toString())
//        3. 보너스 번호 선정
//        mWinNumList.forEachIndexed { index, winNum ->
        //                               ㄴ  index가 아닌 i 로도 표현하기도 함
//            mWinNumViewList[index].text = winNum.toString()
//        }
        for ((i, winNum)in mWinNumList.withIndex()) {
            mWinNumViewList[i].text = winNum.toString()
        }
        //

    }

    fun checkLottoRang() {
        //        4. 비교
//        순위선정 ( 텍스트뷰 출력 )

    }
}