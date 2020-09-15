
class QueensAttack{
    fun test() {
        var n: Int = 8 //배열 크기
        var k: Int = 1 //obstacle 수
        var r_q: Int =4   // 퀸 y 축
        var c_q: Int =4   // 퀸 x 축
        var obstacles: Array<Array<Int>> =  arrayOf(arrayOf(3,5))   //obstacle 위치 [[y,x]]

        var result = 0
        var array = Array(n, {Array(n,{i -> false })})

        //퀸으로부터 1사분면
        var y = r_q-1
        var x = c_q-1

        w@while(y < n && x >= 0) {
            y += 1
            x -= 1
            //println("y : " + y + " , x : " + x)

            //obstacle 들 방어
            //-1해줘야함
            var check = true
            obstacles.forEach {

                var obsY = it[0]-1
                var obsX = it[1]-1
                //println("obsY : " + obsY + " , obsX : " + obsX)
                if(obsY == y && obsX == x) {
                    check = false
                    return@forEach
                }

            }

            if(check) {

                if(y < n && x < n && x >=0 && y>=0) {
                    array[y][x] = true
                    result += 1
                }
            }else {
                break@w
            }

        }
        //println("1사분면 ========")
        //2사분면
        y = r_q-1
        x = c_q-1
        w1@while(y < n && x <n) {
            y += 1
            x += 1
            //println("y : " + y + " , x : " + x)

            //obstacle 들 방어
            //-1해줘야함
            var check = true
            obstacles.forEach {

                var obsY = it[0]-1
                var obsX = it[1]-1
                //println("obsY : " + obsY + " , obsX : " + obsX)
                if(obsY == y && obsX == x) {
                    check = false
                    return@forEach
                }

            }

            if(check) {


                if(y < n && x < n && x >=0 && y>=0) {
                    array[y][x] = true
                    result += 1
                }
            }else {
                break@w1
            }
        }

        //println("2사분면 ========")
        //3사분면
        y = r_q-1
        x = c_q-1
        w2@while(y >=0 && x >=0) {
            y -= 1
            x -= 1
            //println("y : " + y + " , x : " + x)
            //obstacle 들 방어
            //-1해줘야함
            var check = true
            obstacles.forEach {

                var obsY = it[0]-1
                var obsX = it[1]-1
                //println("obsY : " + obsY + " , obsX : " + obsX)
                if(obsY == y && obsX == x) {
                    check = false
                    return@forEach
                }

            }

            if(check) {

                if(y < n && x < n && x >=0 && y>=0) {
                    array[y][x] = true
                    result += 1
                }
            }else {
                break@w2
            }
        }
        //println("3사분면 ========")
        //4사분면
        y = r_q-1
        x = c_q-1
        w3@while(y >=0 && x <n) {
            y -= 1
            x += 1
            //println("y : " + y + " , x : " + x)

            //obstacle 들 방어
            //-1해줘야함
            var check = true
            obstacles.forEach {

                var obsY = it[0]-1
                var obsX = it[1]-1
                //println("obsY : " + obsY + " , obsX : " + obsX)
                if(obsY == y && obsX == x) {
                    check = false
                    return@forEach
                }

            }

            if(check) {

                if(y < n && x < n && x >=0 && y>=0) {
                    array[y][x] = true
                    result += 1
                }
            }else {
                break@w3
            }
        }
        //println("4사분면 ========")
        //왼쪽 가로
        y = r_q-1
        x = c_q-1
        w4@while(x >=0) {
            x -= 1
            //println("y : " + y + " , x : " + x)
            //obstacle 들 방어
            //-1해줘야함
            var check = true
            obstacles.forEach {

                var obsY = it[0]-1
                var obsX = it[1]-1
                //println("obsY : " + obsY + " , obsX : " + obsX)
                if(obsY == y && obsX == x) {
                    check = false
                    return@forEach
                }

            }

            if(check) {


                if(y < n && x < n && x >=0 && y>=0) {
                    array[y][x] = true
                    result += 1
                }
            }else {
                break@w4
            }
        }
        //println("왼쪽 가로 ========")
        //오른쪽 가로
        y = r_q-1
        x = c_q-1
        w5@while(x < n) {
            x += 1
            //println("y : " + y + " , x : " + x)
            //obstacle 들 방어
            //-1해줘야함
            var check = true
            obstacles.forEach {

                var obsY = it[0]-1
                var obsX = it[1]-1
                //println("obsY : " + obsY + " , obsX : " + obsX)
                if(obsY == y && obsX == x) {
                    check = false
                    return@forEach
                }

            }

            if(check) {


                if(y < n && x < n && x >=0 && y>=0) {
                    array[y][x] = true
                    result += 1
                }
            }else {
                break@w5
            }
        }
        //println("오른쪽 가로 ========")
        //위쪽 세로
        y = r_q-1
        x = c_q-1
        w6@while(y < n) {
            y += 1
            //println("y : " + y + " , x : " + x)
            //obstacle 들 방어
            //-1해줘야함
            var check = true
            obstacles.forEach {

                var obsY = it[0]-1
                var obsX = it[1]-1
                //println("obsY : " + obsY + " , obsX : " + obsX)
                if(obsY == y && obsX == x) {
                    check = false
                    return@forEach
                }

            }

            if(check) {


                if(y < n && x < n && x >=0 && y>=0) {
                    array[y][x] = true
                    result += 1
                }
            }else {
                break@w6
            }
        }
        //println("위쪽 로 ========")
        //아래쪽 세로
        y = r_q-1
        x = c_q-1
        w7@while(y >=0) {

            y -= 1
            //println("y : " + y + " , x : " + x)
            //obstacle 들 방어
            //-1해줘야함
            var check = true
            obstacles.forEach {

                var obsY = it[0]-1
                var obsX = it[1]-1
                //println("obsY : " + obsY + " , obsX : " + obsX)
                if(obsY == y && obsX == x) {
                    check = false
                    return@forEach
                }

            }

            if(check) {

                if(y < n && x < n && x >=0 && y>=0) {
                    array[y][x] = true
                    result += 1
                }
            }else {
                break@w7
            }
        }
        //println("아래쪽 세로 ========")
        println(result)


    }
}
