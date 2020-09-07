class sherlockAndAnagrams {
fun solve1() {
        var s = "kkkk"
        var sc = s.toCharArray()
        var next = s.length-1

        var result = 0


        for (index in 0..next) {

            var list = emptyList<String>()
            f@for(j in 0..(sc.size-1)) {
                var st = StringBuilder()
                for(u in 0..index) {
                    if(j+u < sc.size) {
                        st.append(sc[j + u])
                    }else {
                        break@f
                    }
                }

                list = list.plus(st.toString())

            }

//            list.forEach {
//                println(it)
//            }

            //여기서 검토
            var size = list.size-1
            for(li in 0..size-1) {
                //println("-------")
                for(lj in li+1..size) {
                    //println("=======")
                    var ssize = list[li].length-1
                    //println("ssize : " + ssize)
                    var check = true
                    var ljs = list[lj]
                    f1@for (lu in 0..ssize) {
                        //println("vs : " + list[li] + " , " + ljs)

                        if(!ljs.contains(list[li][lu])) {
                            check = false
                            break@f1
                        }else {
                            f2@for (lt in 0..ssize) {
                                if(ljs[lt] == list[li][lu]) {
                                    var tempArray = ljs.toCharArray()
                                    tempArray[lt]=' '
                                    var tempString = StringBuilder()
                                    tempArray.forEach {
                                        tempString.append(it)
                                    }
                                    ljs = tempString.toString()
                                    break@f2
                                }
                            }
                        }
                    }

                    if(check) {
                        //println("결과 : " + list[li] + " , " + list[lj])
                        result += 1
                    }
                }
            }

        }


        println(result)

    }
    }
