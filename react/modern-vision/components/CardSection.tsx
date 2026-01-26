
import React from 'react';
import { ArrowUpRight } from 'lucide-react';
import { CARDS } from '../constants';

const CardSection: React.FC = () => {
  return (
    <section className="py-24 bg-slate-50 overflow-hidden" id="서비스">
      <div className="container mx-auto px-6">
        <div className="flex flex-col md:flex-row justify-between items-end mb-16 gap-6">
          <div className="max-w-2xl">
            <h2 className="text-indigo-600 font-bold tracking-widest uppercase text-sm mb-4">OUR SERVICES</h2>
            <h3 className="text-4xl md:text-5xl font-bold text-slate-900 leading-tight">
              탁월한 비즈니스 성장을 위한<br />핵심 솔루션 패키지
            </h3>
          </div>
          <p className="text-slate-500 max-w-sm mb-2">
            최신 트렌드와 기술력을 바탕으로 귀사의 브랜드 가치를 극대화하는 맞춤형 서비스를 제공합니다.
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
          {CARDS.map((card) => (
            <div 
              key={card.id} 
              className="group bg-white rounded-3xl overflow-hidden shadow-sm hover:shadow-2xl transition-all duration-500 border border-slate-100 flex flex-col"
            >
              <div className="relative h-64 overflow-hidden">
                <img 
                  src={card.imageUrl} 
                  alt={card.title} 
                  className="w-full h-full object-cover transition-transform duration-700 group-hover:scale-110"
                />
                <div className="absolute top-4 left-4">
                  <span className="bg-white/90 backdrop-blur-md text-slate-900 text-xs font-bold px-3 py-1 rounded-full shadow-sm">
                    {card.category}
                  </span>
                </div>
              </div>
              <div className="p-8 flex-grow flex flex-col">
                <h4 className="text-xl font-bold text-slate-900 mb-3 group-hover:text-indigo-600 transition-colors">
                  {card.title}
                </h4>
                <p className="text-slate-600 text-sm leading-relaxed mb-6 flex-grow">
                  {card.description}
                </p>
                <div className="flex items-center justify-between mt-auto pt-6 border-t border-slate-50">
                  <span className="text-indigo-600 font-bold">{card.price}</span>
                  <button className="w-10 h-10 rounded-full bg-slate-100 flex items-center justify-center text-slate-400 group-hover:bg-indigo-600 group-hover:text-white transition-all">
                    <ArrowUpRight size={18} />
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>

        <div className="mt-20 text-center">
          <button className="px-10 py-4 bg-slate-900 text-white rounded-full font-bold hover:bg-slate-800 transition-colors inline-flex items-center gap-2 group">
            모든 서비스 확인하기
            <ArrowUpRight size={20} className="group-hover:translate-x-1 group-hover:-translate-y-1 transition-transform" />
          </button>
        </div>
      </div>
    </section>
  );
};

export default CardSection;
