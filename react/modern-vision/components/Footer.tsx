
import React from 'react';
import { Github, Twitter, Instagram, Linkedin, Mail, Phone, MapPin } from 'lucide-react';

const Footer: React.FC = () => {
  return (
    <footer className="bg-slate-900 text-slate-300 pt-20 pb-10">
      <div className="container mx-auto px-6">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-12 mb-16">
          {/* Brand Column */}
          <div className="space-y-6">
            <div className="flex items-center gap-2">
              <div className="w-8 h-8 bg-indigo-500 rounded flex items-center justify-center text-white font-bold">V</div>
              <span className="text-white text-xl font-bold">VISION</span>
            </div>
            <p className="text-slate-400 leading-relaxed">
              우리는 디자인과 기술의 조화를 통해 세상에 없던 새로운 경험을 창조합니다. 더 나은 미래를 디자인하는 비전과 함께하세요.
            </p>
            <div className="flex gap-4">
              {[Instagram, Twitter, Github, Linkedin].map((Icon, i) => (
                <a key={i} href="#" className="w-10 h-10 rounded-full bg-slate-800 flex items-center justify-center hover:bg-indigo-600 hover:text-white transition-all">
                  <Icon size={18} />
                </a>
              ))}
            </div>
          </div>

          {/* Quick Links */}
          <div>
            <h4 className="text-white font-bold mb-6 text-lg">회사 정보</h4>
            <ul className="space-y-4">
              {['소개', '채용 정보', '브랜드 가이드', '뉴스룸'].map((link) => (
                <li key={link}><a href="#" className="hover:text-indigo-400 transition-colors">{link}</a></li>
              ))}
            </ul>
          </div>

          {/* Services */}
          <div>
            <h4 className="text-white font-bold mb-6 text-lg">주요 서비스</h4>
            <ul className="space-y-4">
              {['UX 리서치', '디지털 제품 설계', '웹 개발', '마케팅 대행'].map((service) => (
                <li key={service}><a href="#" className="hover:text-indigo-400 transition-colors">{service}</a></li>
              ))}
            </ul>
          </div>

          {/* Contact */}
          <div>
            <h4 className="text-white font-bold mb-6 text-lg">문의하기</h4>
            <ul className="space-y-4">
              <li className="flex items-start gap-3">
                <MapPin className="text-indigo-500 shrink-0" size={20} />
                <span>서울특별시 강남구 테헤란로 123, 비전 타워 15층</span>
              </li>
              <li className="flex items-center gap-3">
                <Phone className="text-indigo-500 shrink-0" size={20} />
                <span>02-1234-5678</span>
              </li>
              <li className="flex items-center gap-3">
                <Mail className="text-indigo-500 shrink-0" size={20} />
                <span>hello@vision-design.co.kr</span>
              </li>
            </ul>
          </div>
        </div>

        <div className="pt-10 border-t border-slate-800 flex flex-col md:flex-row justify-between items-center gap-6">
          <p className="text-sm text-slate-500 italic">
            © 2024 Modern Vision Design Studio. All rights reserved.
          </p>
          <div className="flex gap-8 text-sm">
            <a href="#" className="hover:text-white transition-colors">이용약관</a>
            <a href="#" className="hover:text-white transition-colors">개인정보처리방침</a>
            <a href="#" className="hover:text-white transition-colors">쿠키 설정</a>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
