using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookmakerClientApp.Data.Model
{
    public class TempObject
    {
        private String text1;                          
        private String text2;
        private String text3;
        private String text4;
        private String text5;
        private String text6;

        public TempObject(string text1, string text2, string text3, string text4, string text5, string text6)
        {
            this.Text1 = text1;
            this.Text2 = text2;
            this.Text3 = text3;
            this.Text4 = text4;
            this.Text5 = text5;
            this.Text6 = text6;
        }

        public string Text1 { get => text1; set => text1 = value; }
        public string Text2 { get => text2; set => text2 = value; }
        public string Text3 { get => text3; set => text3 = value; }
        public string Text4 { get => text4; set => text4 = value; }
        public string Text5 { get => text5; set => text5 = value; }
        public string Text6 { get => text6; set => text6 = value; }
    }
}
