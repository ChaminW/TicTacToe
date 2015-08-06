package play;

import java.util.Arrays;
import java.util.Random;
import org.apache.log4j.Logger;

public class Controller {
private static Logger loger = Logger.getLogger(Controller.class);
    public int nextChoice(int[] buttonData, int[] eo, int com, int user) {

loger.info("Hard - buttons are "+Arrays.toString(buttonData)+ " and eo list is " + Arrays.toString(eo));
        int[] tempButtonData = buttonData;
        //then check is their any place obvious to return, input button details with 1,0 and enter null buttons as 2
        int returnValue = obviousNextChoise(tempButtonData, com, user);
        //System.out.println(returnValue);
        //else go to logic
        if (returnValue == 0) {
            int rotatedTimes = -1;
            for (int r = 0; r < 4; r++) {
                returnValue = tempNextChoice(eo);
                rotatedTimes = r;
                if (returnValue == 0) {
                    eo = rotateDataOnce(eo);
                } else {
                    break;
                }
            }
            if (returnValue != 0) {
                if (rotatedTimes == 0) {
                    return returnValue;
                } else if (rotatedTimes == 1) {
                    return rotateOnceBack(returnValue);
                } else if (rotatedTimes == 2) {
                    return rotateOnceBack(rotateOnceBack(returnValue));
                } else if (rotatedTimes == 3) {
                    return rotateOnceBack(rotateOnceBack(rotateOnceBack(returnValue)));
                }
            }

        }
        if (returnValue == 0) {
            for (int we = 0; we < buttonData.length; we++) {
                if (buttonData[we] == 2) {
                    return we + 1;
                }
            }
        }
        return returnValue;
    }

    
    public int mediumChoice(int[] buttonData, int com, int user){
        loger.info("Medium - buttons are "+Arrays.toString(buttonData));
        int[] tempButtonData = buttonData;
        //then check is their any place obvious to return, input button details with 1,0 and enter null buttons as 2
        int returnValue = obviousNextChoise(tempButtonData, com, user);
        if (returnValue==0){
            returnValue = eazyChoise(buttonData, com, user);
        }
        return returnValue;
    }
    
    
    public int eazyChoise(int[] buttonData, int com, int user) {
loger.info("Eazy - buttons are "+Arrays.toString(buttonData));
        int lenth = 0;
        for (int u = 0; u < 9; u++) {
            if (buttonData[u] == 2) {
                lenth++;
            }
        }
        int temp = (new Random().nextInt(lenth)) + 1;
        int yu = 0;
        int returnValue = 0;
        for (int u = 0; yu < temp; u++) {
            if (buttonData[u] == 2) {
                returnValue = u;
                yu++;
            }
        }
        return returnValue;
    }

    public int checkForWin(int[] buttonData, int com, int user) {
        //first check for win return -1 if com win, return -2 if user win, else 0
loger.info("For check winner - buttons are "+Arrays.toString(buttonData));
        if (buttonData[1] == com & buttonData[0] == com & buttonData[2] == com) {
            return -1;
        } else if (buttonData[1] == com & buttonData[2] == com & buttonData[0] == com) {
            return -1;
        } else if (buttonData[2] == com & buttonData[0] == com & buttonData[1] == com) {
            return -1;
        } else if (buttonData[3] == com & buttonData[4] == com & buttonData[5] == com) {
            return -1;
        } else if (buttonData[4] == com & buttonData[5] == com & buttonData[3] == com) {
            return -1;
        } else if (buttonData[3] == com & buttonData[5] == com & buttonData[4] == com) {
            return -1;
        } else if (buttonData[6] == com & buttonData[7] == com & buttonData[8] == com) {
            return -1;
        } else if (buttonData[8] == com & buttonData[7] == com & buttonData[6] == com) {
            return -1;
        } else if (buttonData[6] == com & buttonData[8] == com & buttonData[7] == com) {
            return -1;
        } else if (buttonData[3] == com & buttonData[0] == com & buttonData[6] == com) {
            return -1;
        } else if (buttonData[6] == com & buttonData[0] == com & buttonData[3] == com) {
            return -1;
        } else if (buttonData[3] == com & buttonData[6] == com & buttonData[0] == com) {
            return -1;
        } else if (buttonData[1] == com & buttonData[4] == com & buttonData[7] == com) {
            return -1;
        } else if (buttonData[7] == com & buttonData[4] == com & buttonData[1] == com) {
            return -1;
        } else if (buttonData[1] == com & buttonData[7] == com & buttonData[4] == com) {
            return -1;
        } else if (buttonData[8] == com & buttonData[5] == com & buttonData[2] == com) {
            return -1;
        } else if (buttonData[5] == com & buttonData[2] == com & buttonData[8] == com) {
            return -1;
        } else if (buttonData[2] == com & buttonData[8] == com & buttonData[5] == com) {
            return -1;
        } else if (buttonData[4] == com & buttonData[0] == com & buttonData[8] == com) {
            return -1;
        } else if (buttonData[8] == com & buttonData[0] == com & buttonData[4] == com) {
            return -1;
        } else if (buttonData[4] == com & buttonData[8] == com & buttonData[0] == com) {
            return -1;
        } else if (buttonData[6] == com & buttonData[4] == com & buttonData[2] == com) {
            return -1;
        } else if (buttonData[2] == com & buttonData[6] == com & buttonData[4] == com) {
            return -1;
        } else if (buttonData[2] == com & buttonData[4] == com & buttonData[6] == com) {
            return -1;
        } //-----------------------------------------   
        else if (buttonData[1] == user & buttonData[0] == user & buttonData[2] == user) {
            return -2;
        } else if (buttonData[1] == user & buttonData[2] == user & buttonData[0] == user) {
            return -2;
        } else if (buttonData[2] == user & buttonData[0] == user & buttonData[1] == user) {
            return -2;
        } else if (buttonData[3] == user & buttonData[4] == user & buttonData[5] == user) {
            return -2;
        } else if (buttonData[4] == user & buttonData[5] == user & buttonData[3] == user) {
            return -2;
        } else if (buttonData[3] == user & buttonData[5] == user & buttonData[4] == user) {
            return -2;
        } else if (buttonData[6] == user & buttonData[7] == user & buttonData[8] == user) {
            return -2;
        } else if (buttonData[8] == user & buttonData[7] == user & buttonData[6] == user) {
            return -2;
        } else if (buttonData[6] == user & buttonData[8] == user & buttonData[7] == user) {
            return -2;
        } else if (buttonData[3] == user & buttonData[0] == user & buttonData[6] == user) {
            return -2;
        } else if (buttonData[6] == user & buttonData[0] == user & buttonData[3] == user) {
            return -2;
        } else if (buttonData[3] == user & buttonData[6] == user & buttonData[0] == user) {
            return -2;
        } else if (buttonData[1] == user & buttonData[4] == user & buttonData[7] == user) {
            return -2;
        } else if (buttonData[7] == user & buttonData[4] == user & buttonData[1] == user) {
            return -2;
        } else if (buttonData[1] == user & buttonData[7] == user & buttonData[4] == user) {
            return -2;
        } else if (buttonData[8] == user & buttonData[5] == user & buttonData[2] == user) {
            return -2;
        } else if (buttonData[5] == user & buttonData[2] == user & buttonData[8] == user) {
            return -2;
        } else if (buttonData[2] == user & buttonData[8] == user & buttonData[5] == user) {
            return -2;
        } else if (buttonData[4] == user & buttonData[0] == user & buttonData[8] == user) {
            return -2;
        } else if (buttonData[8] == user & buttonData[0] == user & buttonData[4] == user) {
            return -2;
        } else if (buttonData[4] == user & buttonData[8] == user & buttonData[0] == user) {
            return -2;
        } else if (buttonData[6] == user & buttonData[4] == user & buttonData[2] == user) {
            return -2;
        } else if (buttonData[2] == user & buttonData[6] == user & buttonData[4] == user) {
            return -2;
        } else if (buttonData[2] == user & buttonData[4] == user & buttonData[6] == user) {
            return -2;
        }
        return 0;
    }

    public int obviousNextChoise(int[] buttonData, int com, int user) {
        if (buttonData[1] == com & buttonData[0] == com & buttonData[2] == 2) {
            return 3;
        } else if (buttonData[1] == com & buttonData[2] == com & buttonData[0] == 2) {
            return 1;
        } else if (buttonData[2] == com & buttonData[0] == com & buttonData[1] == 2) {
            return 2;
        } else if (buttonData[3] == com & buttonData[4] == com & buttonData[5] == 2) {
            return 6;
        } else if (buttonData[4] == com & buttonData[5] == com & buttonData[3] == 2) {
            return 4;
        } else if (buttonData[3] == com & buttonData[5] == com & buttonData[4] == 2) {
            return 5;
        } else if (buttonData[6] == com & buttonData[7] == com & buttonData[8] == 2) {
            return 9;
        } else if (buttonData[8] == com & buttonData[7] == com & buttonData[6] == 2) {
            return 7;
        } else if (buttonData[6] == com & buttonData[8] == com & buttonData[7] == 2) {
            return 8;
        } else if (buttonData[3] == com & buttonData[0] == com & buttonData[6] == 2) {
            return 7;
        } else if (buttonData[6] == com & buttonData[0] == com & buttonData[3] == 2) {
            return 4;
        } else if (buttonData[3] == com & buttonData[6] == com & buttonData[0] == 2) {
            return 1;
        } else if (buttonData[1] == com & buttonData[4] == com & buttonData[7] == 2) {
            return 8;
        } else if (buttonData[7] == com & buttonData[4] == com & buttonData[1] == 2) {
            return 2;
        } else if (buttonData[1] == com & buttonData[7] == com & buttonData[4] == 2) {
            return 5;
        } else if (buttonData[8] == com & buttonData[5] == com & buttonData[2] == 2) {
            return 3;
        } else if (buttonData[5] == com & buttonData[2] == com & buttonData[8] == 2) {
            return 9;
        } else if (buttonData[2] == com & buttonData[8] == com & buttonData[5] == 2) {
            return 6;
        } else if (buttonData[4] == com & buttonData[0] == com & buttonData[8] == 2) {
            return 9;
        } else if (buttonData[8] == com & buttonData[0] == com & buttonData[4] == 2) {
            return 5;
        } else if (buttonData[4] == com & buttonData[8] == com & buttonData[0] == 2) {
            return 1;
        } else if (buttonData[6] == com & buttonData[4] == com & buttonData[2] == 2) {
            return 3;
        } else if (buttonData[2] == com & buttonData[6] == com & buttonData[4] == 2) {
            return 5;
        } else if (buttonData[2] == com & buttonData[4] == com & buttonData[6] == 2) {
            return 7;
        } else if (buttonData[1] == user & buttonData[0] == user & buttonData[2] == 2) {
            return 3;
        } else if (buttonData[1] == user & buttonData[2] == user & buttonData[0] == 2) {
            return 1;
        } else if (buttonData[2] == user & buttonData[0] == user & buttonData[1] == 2) {
            return 2;
        } else if (buttonData[3] == user & buttonData[4] == user & buttonData[5] == 2) {
            return 6;
        } else if (buttonData[4] == user & buttonData[5] == user & buttonData[3] == 2) {
            return 4;
        } else if (buttonData[3] == user & buttonData[5] == user & buttonData[4] == 2) {
            return 5;
        } else if (buttonData[6] == user & buttonData[7] == user & buttonData[8] == 2) {
            return 9;
        } else if (buttonData[8] == user & buttonData[7] == user & buttonData[6] == 2) {
            return 7;
        } else if (buttonData[6] == user & buttonData[8] == user & buttonData[7] == 2) {
            return 8;
        } else if (buttonData[3] == user & buttonData[0] == user & buttonData[6] == 2) {
            return 7;
        } else if (buttonData[6] == user & buttonData[0] == user & buttonData[3] == 2) {
            return 4;
        } else if (buttonData[3] == user & buttonData[6] == user & buttonData[0] == 2) {
            return 1;
        } else if (buttonData[1] == user & buttonData[4] == user & buttonData[7] == 2) {
            return 8;
        } else if (buttonData[7] == user & buttonData[4] == user & buttonData[1] == 2) {
            return 2;
        } else if (buttonData[1] == user & buttonData[7] == user & buttonData[4] == 2) {
            return 5;
        } else if (buttonData[8] == user & buttonData[5] == user & buttonData[2] == 2) {
            return 3;
        } else if (buttonData[5] == user & buttonData[2] == user & buttonData[8] == 2) {
            return 9;
        } else if (buttonData[2] == user & buttonData[8] == user & buttonData[5] == 2) {
            return 6;
        } else if (buttonData[4] == user & buttonData[0] == user & buttonData[8] == 2) {
            return 9;
        } else if (buttonData[8] == user & buttonData[0] == user & buttonData[4] == 2) {
            return 5;
        } else if (buttonData[4] == user & buttonData[8] == user & buttonData[0] == 2) {
            return 1;
        } else if (buttonData[6] == user & buttonData[4] == user & buttonData[2] == 2) {
            return 3;
        } else if (buttonData[2] == user & buttonData[6] == user & buttonData[4] == 2) {
            return 5;
        } else if (buttonData[2] == user & buttonData[4] == user & buttonData[6] == 2) {
            return 7;
        }
        return 0;
    }

    public int rotateOnceBack(int k) {
        if (k == 3) {
            return 1;
        } else if (k == 6) {
            return 2;
        } else if (k == 9) {
            return 3;
        } else if (k == 2) {
            return 4;
        } else if (k == 5) {
            return 5;
        } else if (k == 8) {
            return 6;
        } else if (k == 1) {
            return 7;
        } else if (k == 4) {
            return 8;
        } else if (k == 7) {
            return 9;
        } else {
            return 0;
        }
    }

    public int[] rotateDataOnce(int[] currentData) {//problem here
        int[] rotatedData = new int[currentData.length];
        for (int y = 0; y < currentData.length; y++) {
            if (currentData[y] == 1) {
                rotatedData[y] = 3;
            } else if (currentData[y] == 2) {
                rotatedData[y] = 6;
            } else if (currentData[y] == 3) {
                rotatedData[y] = 9;
            } else if (currentData[y] == 4) {
                rotatedData[y] = 2;
            } else if (currentData[y] == 5) {
                rotatedData[y] = 5;
            } else if (currentData[y] == 6) {
                rotatedData[y] = 8;
            } else if (currentData[y] == 7) {
                rotatedData[y] = 1;
            } else if (currentData[y] == 8) {
                rotatedData[y] = 4;
            } else if (currentData[y] == 9) {
                rotatedData[y] = 7;
            }
        }
        return rotatedData;
    }

    public int tempNextChoice(int[] eo) {
        loger.info("For process the logic, eo list is " + Arrays.toString(eo));
//eo = entered order
        if (eo.length == 1) {
            int t = eo[0];
            if (t == 1) {
                return 5;
            } else if (t == 2) {
                return 3;
            } else if (t == 5) {
                return 1;
            }
        } else if (eo.length == 3) {

            int t1 = eo[0];
            int t2 = eo[1];
            int t3 = eo[2];

            if (t1 == 1 & t2 == 5 & t3 == 9) {
                return 2;
            } else if (t1 == 1 & t2 == 5 & t3 == 6) {
                return 9;
            } else if (t1 == 1 & t2 == 5 & t3 == 8) {
                return 9;
            } else if (t1 == 2 & t2 == 3 & t3 == 1) {
                return 9;
            } else if (t1 == 2 & t2 == 3 & t3 == 4) {
                return 9;
            } else if (t1 == 2 & t2 == 3 & t3 == 5) {
                return 8;
            } else if (t1 == 2 & t2 == 3 & t3 == 6) {
                return 5;
            } else if (t1 == 2 & t2 == 3 & t3 == 9) {
                return 8;
            } else if (t1 == 2 & t2 == 3 & t3 == 7) {
                return 5;
            } else if (t1 == 5 & t2 == 1 & t3 == 2) {
                return 8;
            } else if (t1 == 5 & t2 == 1 & t3 == 9) {
                return 3;
            }
        } else if (eo.length == 5) {
            if (eo[0] == 2 & eo[1] == 3 & eo[2] == 1 & eo[3] == 9 & eo[4] == 6) {
                return 7;
            }
        }
        return 0;
    }
}
