-- 1. Customers table (linked to Supabase Auth)
CREATE TABLE public.customers (
    id UUID PRIMARY KEY REFERENCES auth.users ON DELETE CASCADE,
    name TEXT,
    email TEXT UNIQUE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 2. Activities table (Suggestions from ActivityDataSource)
CREATE TABLE public.activities (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    why_fits TEXT NOT NULL,
    first_step TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 3. Journal entries table
CREATE TABLE public.journal_entries (
    id SERIAL PRIMARY KEY,
    customer_id UUID REFERENCES public.customers(id) ON DELETE CASCADE,
    content TEXT NOT NULL,
    entry_date DATE DEFAULT CURRENT_DATE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 4. User stats table (Points and Mission status)
CREATE TABLE public.user_stats (
    customer_id UUID PRIMARY KEY REFERENCES public.customers(id) ON DELETE CASCADE,
    total_points INT DEFAULT 0,
    last_mission_reset DATE,
    journal_mission_completed BOOLEAN DEFAULT FALSE,
    goal_mission_completed BOOLEAN DEFAULT FALSE,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Enable Row Level Security (RLS)
ALTER TABLE public.customers ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.journal_entries ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.user_stats ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.activities ENABLE ROW LEVEL SECURITY;

-- Policies for Customers (Users can only see/edit their own data)
CREATE POLICY "Users can view their own profile" ON public.customers FOR SELECT USING (auth.uid() = id);
CREATE POLICY "Users can update their own profile" ON public.customers FOR UPDATE USING (auth.uid() = id);

-- Policies for Journal Entries
CREATE POLICY "Users can view their own journal" ON public.journal_entries FOR SELECT USING (auth.uid() = customer_id);
CREATE POLICY "Users can insert their own journal" ON public.journal_entries FOR INSERT WITH CHECK (auth.uid() = customer_id);
CREATE POLICY "Users can update their own journal" ON public.journal_entries FOR UPDATE USING (auth.uid() = customer_id);

-- Policies for User Stats
CREATE POLICY "Users can view their own stats" ON public.user_stats FOR SELECT USING (auth.uid() = customer_id);
CREATE POLICY "Users can update their own stats" ON public.user_stats FOR UPDATE USING (auth.uid() = customer_id);

-- Policies for Activities (Anyone can read)
CREATE POLICY "Allow public read access to activities" ON public.activities FOR SELECT USING (true);
