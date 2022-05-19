package com.neppplus.a20220519_practice_lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
//내 번호 6개 저장
    var mMyNumList = arrayOf(3,5,7,8,9,11)
//    var mMyNumList2 = arrayListOf<int>(3,5,7,8,9,11)
//                                  ㄴ뒤에 값이 있기 때문에 <int>가 필요없다
    // 컴퓨터가 뽑은 당첨번호를 6개 저장할 ArrayList 만들어 주자
    val mWinNumList = ArrayList<Int>()
    //텍스트 ArrayList
    val mWinNumViewList = ArrayList<TextView>()
    //보너스 숫자 저장용 멤버 변수 생성
    var mBonusNum = 0
    var mUserMoney = 0
    var mEarnedMoney : Long = 0
    // var mEarnedMoney = 0L
    //                   ㄴ int 표기 가능 값 이상으로 표시하고 싶을때
    var firstCount = 0
    var secondCount = 0
    var thirdCount =0
    var fourthCount = 0
    var fifthCount = 0
    var loseCount = 0

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
//
//        2. 당첨 번호 정렬 (sort) -> 작은 수 - 큰 수 -> 텍스트뷰에 표현
        mWinNumList.sort()
//        Log.d("당첨 번호",mWinNumList.toString())


//        mWinNumList.forEachIndexed { index, winNum ->
        //                               ㄴ  index가 아닌 i 로도 표현하기도 함
//            mWinNumViewList[index].text = winNum.toString()
//        }
        for ((i, winNum)in mWinNumList.withIndex()) {
            mWinNumViewList[i].text = winNum.toString()
        }
//        3. 보너스 번호 선정
        while (true) {
            val randomNum = (Math.random()*45+1).toInt()

            if (!mWinNumList.contains(randomNum)) {
                mBonusNum = randomNum
                BonuseNum1.text = mBonusNum.toString()
                break
            }
        }
        checkLottoRang()

        //

    }

    fun checkLottoRang() {
        //        4. 비교

        var correctCount = 0
        for (myNum in mMyNumList )

            // ㄴ 내 번호를 하나씩 조회한다는 구문
            if (mWinNumList.contains(myNum)) {
                correctCount++
            }
//        순위선정 ( 텍스트뷰 출력 )

        when (correctCount) {
            6 -> {
                mEarnedMoney += 300000000
                firstCount++
                Toast.makeText(this, "1등", Toast.LENGTH_SHORT).show()
            }

            5 -> { if (mMyNumList.contains(mBonusNum)){
                mEarnedMoney += 50000000
                secondCount++
                Toast.makeText(this, "2등", Toast.LENGTH_SHORT).show() }
            }
            4 -> {
                mEarnedMoney += 3000000
                thirdCount++
                Toast.makeText(this, "3등", Toast.LENGTH_SHORT).show()
            }
            3 -> {
                mEarnedMoney += 50000
                fourthCount++
                Toast.makeText(this, "4등", Toast.LENGTH_SHORT).show()
            }

         else -> {
             loseCount++
             Toast.makeText(this, "낙첨", Toast.LENGTH_SHORT).show()}
            }
        usedMoney.text = "사용 금액 : ${NumberFormat.getInstance().format(mUserMoney)}원"
        earnMoneyTxt.text = "당첨 누적 금액 : ${NumberFormat.getInstance().format(mEarnedMoney)}원"
        winRank1.text = "1등 당첨 횟수 : ${firstCount}회"
        winRank2.text = "2등 당첨 횟수 : ${secondCount}회"
        winRank3.text = "3등 당첨 횟수 : ${thirdCount}회"
        winRank4.text = "4등 당첨 횟수 : ${fourthCount}회"
        winRank5.text = "5등 당첨 횟수 : ${fifthCount}회"
        loseRank.text = "낙첨 횟수 : ${loseCount}회"
    }

}