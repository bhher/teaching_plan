import { createContext, useCallback, useContext, useEffect, useMemo, useState } from 'react';
import { api } from '../api/client';

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [member, setMember] = useState(null);
  const [loading, setLoading] = useState(true);

  const refresh = useCallback(async () => {
    try {
      const { member: m } = await api.me();
      setMember(m);
    } catch {
      setMember(null);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    refresh();
  }, [refresh]);

  const login = useCallback(async (email, password) => {
    const { member: m } = await api.login({ email, password });
    setMember(m);
    return m;
  }, []);

  const logout = useCallback(async () => {
    await api.logout();
    setMember(null);
  }, []);

  const join = useCallback(async (payload) => {
    const { member: m } = await api.join(payload);
    setMember(m);
    return m;
  }, []);

  const value = useMemo(
    () => ({
      member,
      loading,
      refresh,
      login,
      logout,
      join,
    }),
    [member, loading, refresh, login, logout, join]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth must be used within AuthProvider');
  return ctx;
}
