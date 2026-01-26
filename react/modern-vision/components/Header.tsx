
import React, { useState, useEffect } from 'react';
import { Menu, X, ShoppingBag, Search } from 'lucide-react';

const Header: React.FC = () => {
  const [isScrolled, setIsScrolled] = useState(false);
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      setIsScrolled(window.scrollY > 20);
    };
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  return (
    <header 
      className={`fixed top-0 left-0 right-0 z-50 transition-all duration-300 ${
        isScrolled ? 'glass-effect py-3 shadow-md border-b border-white/20' : 'bg-transparent py-6'
      }`}
    >
      <div className="container mx-auto px-6 flex items-center justify-between">
        <div className="flex items-center gap-2">
          <div className="w-10 h-10 bg-indigo-600 rounded-lg flex items-center justify-center text-white font-bold text-xl">
            V
          </div>
          <span className={`text-xl font-bold tracking-tight ${isScrolled ? 'text-slate-900' : 'text-white shadow-sm'}`}>
            VISION
          </span>
        </div>

        {/* Desktop Navigation */}
        <nav className="hidden md:flex items-center gap-8">
          {['서비스', '포트폴리오', '팀 소개', '문의하기'].map((item) => (
            <a 
              key={item} 
              href={`#${item}`} 
              className={`text-sm font-medium transition-colors ${
                isScrolled ? 'text-slate-600 hover:text-indigo-600' : 'text-white/90 hover:text-white'
              }`}
            >
              {item}
            </a>
          ))}
        </nav>

        <div className="flex items-center gap-4">
          <button className={`p-2 rounded-full transition-colors ${isScrolled ? 'hover:bg-slate-200 text-slate-700' : 'hover:bg-white/10 text-white'}`}>
            <Search size={20} />
          </button>
          <button className={`p-2 rounded-full transition-colors ${isScrolled ? 'hover:bg-slate-200 text-slate-700' : 'hover:bg-white/10 text-white'}`}>
            <ShoppingBag size={20} />
          </button>
          <button 
            className="md:hidden p-2 text-slate-700"
            onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
          >
            {mobileMenuOpen ? <X size={24} className={isScrolled ? 'text-slate-900' : 'text-white'} /> : <Menu size={24} className={isScrolled ? 'text-slate-900' : 'text-white'} />}
          </button>
        </div>
      </div>

      {/* Mobile Navigation */}
      {mobileMenuOpen && (
        <div className="md:hidden absolute top-full left-0 right-0 bg-white border-t border-slate-100 shadow-xl py-6 px-6 flex flex-col gap-4 animate-in fade-in slide-in-from-top-4">
          {['서비스', '포트폴리오', '팀 소개', '문의하기'].map((item) => (
            <a key={item} href="#" className="text-lg font-medium text-slate-800 border-b border-slate-50 pb-2">
              {item}
            </a>
          ))}
          <button className="bg-indigo-600 text-white py-3 rounded-xl font-semibold mt-2">
            시작하기
          </button>
        </div>
      )}
    </header>
  );
};

export default Header;
