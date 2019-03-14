package com.chouyarn.flashback_39;

/**
 * Created by chouyarn of BI on 2019/3/14
 */
public class Pattern {
    private boolean matched = false;
    private char[] pattern;
    private int plen;

    public Pattern(char[] pattern,int plen){
        this.pattern = pattern;
        this.plen = plen;
    }

    public boolean match(char[] text,int tlen){
        matched = false;
        rmatch(0,0,text,tlen);
        return matched;
    }

    private void rmatch(int ti,int pj,char[] text,int tlen){
        if (matched) return;
        if(pj == plen){
            if (ti == tlen) matched = true;
            return;
        }
        if (pattern[pj] == '*'){//匹配任意个字符
            for (int k = 0;k <= tlen - ti;++k){
                rmatch(ti+k,pj+1,text,tlen);
            }
        }else if (pattern[pj] == '?'){//匹配0个或者1个字符
            rmatch(ti,pj+1,text,tlen);
            rmatch(ti +1,pj+1,text,tlen);
        }else if (ti < tlen && pattern[pj] == text[ti]){//纯字符匹配才行
            rmatch(ti+1,pj+1,text,tlen);
        }
    }
}
